package com.example.presentation.screen.profile

import com.example.core.base.BaseEffect
import com.example.core.base.BaseEvent
import com.example.core.base.BaseState
import com.example.core.base.ViewStatus


data class ProfileState(override val viewStatus: ViewStatus = ViewStatus.INITIAL) :
    BaseState
data object ProfileEvent : BaseEvent
data object ProfileEffect : BaseEffect