package com.example.presentation.screen.locations

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
class LocationsViewModel @Inject constructor(
    private val repository: RickAndMortyRepository
) : BaseViewModel<LocationsState, LocationsEvent, LocationsEffect>() {

    init {
        setEvent(LocationsEvent.LoadData())
    }

    override fun createInitialState(): LocationsState {
        return LocationsState(viewStatus = ViewStatus.LOADING)
    }

    override fun consume(event: LocationsEvent) {
        when (event) {
            is LocationsEvent.LoadData -> getLocations(event.at)
        }
    }

    private fun getLocations(at: Int) {
        setState { copy(isLoadingMore = true) }
        viewModelScope.launch {
            repository.getLocations(at).cross(
                right = { locations ->
                    delay(3000)
                    val mergedLocations = state.value.locations + locations

                    setState {
                        copy(
                            viewStatus = ViewStatus.SUCCESS,
                            locations = mergedLocations.toImmutableList(),
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