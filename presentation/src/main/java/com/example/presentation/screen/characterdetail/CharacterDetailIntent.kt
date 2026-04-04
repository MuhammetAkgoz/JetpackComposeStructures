package com.example.presentation.screen.characterdetail

import com.example.core.base.BaseEffect
import com.example.core.base.BaseEvent
import com.example.core.base.BaseState
import com.example.core.base.ViewStatus
import com.example.domain.model.CharacterModel


data class CharacterDetailState(
    val character: CharacterModel? = null,
    override val viewStatus: ViewStatus = ViewStatus.SUCCESS
) : BaseState

sealed class CharacterDetailEvent : BaseEvent {
    data class GetCharacterDetail(val id: Int?) : CharacterDetailEvent()
}

data object CharacterDetailEffect : BaseEffect