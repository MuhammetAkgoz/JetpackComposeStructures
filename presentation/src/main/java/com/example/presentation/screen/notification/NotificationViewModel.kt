package com.example.presentation.screen.notification

import com.example.core.base.BaseViewModel
import com.example.core.base.ViewStatus

class NotificationViewModel : BaseViewModel<NotificationState, NotificationEvent, NotificationEffect>() {
    override fun createInitialState(): NotificationState {
        return NotificationState(ViewStatus.INITIAL)
    }

    override fun consume(event: NotificationEvent) {
        TODO("Not yet implemented")
    }

}