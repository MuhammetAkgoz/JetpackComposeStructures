package com.example.presentation.screen.notification

import com.example.core.base.BaseEffect
import com.example.core.base.BaseEvent
import com.example.core.base.BaseState
import com.example.core.base.ViewStatus


data class NotificationState(override val viewStatus: ViewStatus) : BaseState
data object NotificationEvent : BaseEvent
data object NotificationEffect : BaseEffect