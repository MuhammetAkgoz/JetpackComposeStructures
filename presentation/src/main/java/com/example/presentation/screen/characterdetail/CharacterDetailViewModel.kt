package com.example.presentation.screen.characterdetail


import com.example.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor() :
    BaseViewModel<CharacterDetailState, CharacterDetailEvent, CharacterDetailEffect>() {

    override fun createInitialState(): CharacterDetailState {
        return CharacterDetailState
    }

    override fun consume(event: CharacterDetailEvent) {
        when(event){

        }
    }
}