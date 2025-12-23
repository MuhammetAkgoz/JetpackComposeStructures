package com.example.presentation.screen.search

import com.example.core.base.BaseEffect
import com.example.core.base.BaseEvent
import com.example.core.base.BaseState
import com.example.core.base.ViewStatus


data class SearchState(override val viewStatus: ViewStatus) : BaseState
data object SearchEvent : BaseEvent
data object SearchEffect : BaseEffect