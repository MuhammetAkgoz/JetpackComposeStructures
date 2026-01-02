package com.example.presentation.screen.characters

import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseViewModel
import com.example.core.base.ViewStatus
import com.example.core.functional.cross
import com.example.domain.model.asError
import com.example.domain.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: RickAndMortyRepository
) : BaseViewModel<CharactersState, CharacterEvent, CharactersEffect>() {

    override fun createInitialState() = CharactersState(viewStatus = ViewStatus.LOADING)

    init {
        setEvent(CharacterEvent.LoadData())
    }

    override fun consume(event: CharacterEvent) {
        when (event) {
            is CharacterEvent.LoadData -> getCharacters(event.page)
            is CharacterEvent.OnCharacterClick -> setEffect { CharactersEffect.ShowToast(event.url) }
        }
    }


    private fun getCharacters(at: Int) {
        viewModelScope.launch {
            setState { copy(isLoadingMore = true) }

            repository.getCharacters(at).cross(
                right = { characters ->
                    //delay(3000)
                    val mergedCharacter = (state.value.characters + characters)


                    setState {
                        copy(
                            viewStatus = ViewStatus.SUCCESS,
                            characters = mergedCharacter.toImmutableList(),
                            isLoadingMore = false
                        )
                    }
                },
                left = {
                    setState { copy(viewStatus = ViewStatus.ERROR) }
                    setEffect { CharactersEffect.ShowErrorDialog(it.asError()) }
                }
            )
        }
    }
}