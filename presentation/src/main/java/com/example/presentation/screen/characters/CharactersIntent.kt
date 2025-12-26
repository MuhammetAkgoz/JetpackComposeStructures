package com.example.presentation.screen.characters

import com.example.core.base.BaseEffect
import com.example.core.base.BaseEvent
import com.example.core.base.BaseState
import com.example.core.base.ViewStatus
import com.example.domain.model.CharacterModel
import com.example.domain.model.ErrorModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class CharactersState(
    val characters: ImmutableList<CharacterModel> = persistentListOf(),
    val errorMessage: String? = null,
    val isLoadingMore: Boolean = false,

    override val viewStatus: ViewStatus = ViewStatus.INITIAL,
) : BaseState

sealed class CharacterEvent : BaseEvent {
    data class LoadData(val page: Int = 1) : CharacterEvent()
    data class OnCharacterClick(val url: String) : CharacterEvent()
}

sealed class CharactersEffect : BaseEffect {
    data class ShowToast(val url: String) : CharactersEffect()
    data class ShowErrorDialog(val errorModel: ErrorModel) : CharactersEffect()
}