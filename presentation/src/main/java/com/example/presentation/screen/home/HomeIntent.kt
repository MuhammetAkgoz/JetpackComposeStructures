package com.example.presentation.screen.home

import com.example.core.base.BaseEffect
import com.example.core.base.BaseEvent
import com.example.core.base.BaseState
import com.example.core.base.ViewStatus
import com.example.domain.model.CharacterModel
import com.example.domain.model.ErrorModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class HomeState(
    val characters: ImmutableList<CharacterModel> = persistentListOf(),
    val errorMessage: String? = null,

    override val viewStatus: ViewStatus = ViewStatus.INITIAL,
) : BaseState

sealed class HomeEvent : BaseEvent {
    data object LoadData : HomeEvent()
    data class OnCharacterClick(val url: String) : HomeEvent()
}

sealed class HomeEffect : BaseEffect {
    data class ShowToast(val url: String) : HomeEffect()
    data class ShowError(val message: String) : HomeEffect()
    data class ShowErrorDialog(val errorModel: ErrorModel) : HomeEffect()
}