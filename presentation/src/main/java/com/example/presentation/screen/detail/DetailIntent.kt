package com.example.presentation.screen.detail

import com.example.core.base.BaseEffect
import com.example.core.base.BaseEvent
import com.example.core.base.BaseState
import com.example.core.base.ViewStatus


data class DetailState(override val viewStatus: ViewStatus = ViewStatus.INITIAL) : BaseState
data object DetailEvent : BaseEvent
data object DetailEffect : BaseEffect