package com.example.presentation.screen.characterdetail

import com.example.core.base.BaseEffect
import com.example.core.base.BaseEvent
import com.example.core.base.BaseState
import com.example.core.base.ViewStatus


data object CharacterDetailState : BaseState {
    override val viewStatus: ViewStatus
        get() = ViewStatus.SUCCESS
}

data object  CharacterDetailEvent: BaseEvent
data object  CharacterDetailEffect: BaseEffect