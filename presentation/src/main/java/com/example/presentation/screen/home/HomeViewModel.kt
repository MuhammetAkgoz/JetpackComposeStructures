package com.example.presentation.screen.home

import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseViewModel
import com.example.core.base.ViewStatus
import com.example.core.functional.cross
import com.example.domain.model.asError
import com.example.domain.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RickAndMortyRepository
) : BaseViewModel<HomeState, HomeEvent, HomeEffect>() {

    override fun createInitialState() = HomeState(viewStatus = ViewStatus.LOADING)

    init {
        setEvent(HomeEvent.LoadData)
    }

    override fun consume(event: HomeEvent) {
        when (event) {
            is HomeEvent.LoadData -> getCharacters()
            is HomeEvent.OnCharacterClick -> setEffect { HomeEffect.ShowToast(event.url) }
        }
    }


    private fun getCharacters() {
        viewModelScope.launch {
            setState { copy(viewStatus = ViewStatus.LOADING, errorMessage = null) }

            repository.getCharacters().cross(
                right = { characters ->
                    delay(2000)
                    setState {
                        copy(
                            viewStatus = ViewStatus.SUCCESS,
                            characters = characters.toImmutableList()
                        )
                    }
                },
                left = {
                    setState { copy(viewStatus = ViewStatus.ERROR) }
                    setEffect { HomeEffect.ShowErrorDialog(it.asError()) }
                }
            )
        }
    }
}