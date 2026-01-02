package com.example.presentation.screen.episodes

import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseViewModel
import com.example.core.base.ViewStatus
import com.example.core.functional.cross
import com.example.domain.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val repository: RickAndMortyRepository
) : BaseViewModel<EpisodesState, EpisodesEvent, EpisodesEffect>() {

    init {
        setEvent(EpisodesEvent.LoadData())
    }

    override fun createInitialState(): EpisodesState {
        return EpisodesState(viewStatus = ViewStatus.LOADING)
    }

    override fun consume(event: EpisodesEvent) {
        when (event) {
            is EpisodesEvent.LoadData -> getEpisodes(event.at)
        }
    }

    private fun getEpisodes(at: Int) {
        setState { copy(isLoadingMore = true) }
        viewModelScope.launch {
            repository.getEpisodes(at).cross(
                right = { episodes ->
                    delay(3000)
                    val mergedEpisodes = state.value.episodes + episodes

                    setState {
                        copy(
                            viewStatus = ViewStatus.SUCCESS,
                            episodes = mergedEpisodes.toImmutableList(),
                            isLoadingMore = false
                        )
                    }
                },
                left = {
                    setState { copy(viewStatus = ViewStatus.ERROR) }
                }
            )
        }
    }
}
