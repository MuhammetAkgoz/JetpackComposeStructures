package com.example.presentation.screen.characterdetail


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.core.base.BaseViewModel
import com.example.core.functional.cross
import com.example.domain.repository.RickAndMortyRepository
import com.example.presentation.navigation.destinations.CharacterDetailDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: RickAndMortyRepository
) : BaseViewModel<CharacterDetailState, CharacterDetailEvent, CharacterDetailEffect>() {

    override fun createInitialState(): CharacterDetailState {
        return CharacterDetailState();
    }

    init {
        val id = savedStateHandle.toRoute<CharacterDetailDestination>().id;
        setEvent(CharacterDetailEvent.GetCharacterDetail(id))
    }


    override fun consume(event: CharacterDetailEvent) {
        when (event) {
            is CharacterDetailEvent.GetCharacterDetail -> getCharacter(event.id ?: 0)
        }
    }

    private fun getCharacter(id: Int) {
        viewModelScope.launch {
            repository.getCachedCharacterById(id).cross(
                right = { character ->
                    setState {
                        copy(
                            character = character
                        )
                    }
                },
                left = {

                }
            )
        }
    }
}