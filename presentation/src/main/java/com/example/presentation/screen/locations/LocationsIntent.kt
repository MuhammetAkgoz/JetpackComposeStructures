package com.example.presentation.screen.locations

import com.example.core.base.BaseEffect
import com.example.core.base.BaseEvent
import com.example.core.base.BaseState
import com.example.core.base.ViewStatus
import com.example.domain.model.LocationModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf


data class LocationsState(
    override val viewStatus: ViewStatus,

    val isLoadingMore: Boolean = false,
    val locations: ImmutableList<LocationModel> = persistentListOf()
) : BaseState


sealed class LocationsEvent : BaseEvent {
    data class LoadData(val at : Int = 1) : LocationsEvent()
}

data object LocationsEffect : BaseEffect