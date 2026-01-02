package com.example.presentation.screen.episodes

import com.example.core.base.BaseEffect
import com.example.core.base.BaseEvent
import com.example.core.base.BaseState
import com.example.core.base.ViewStatus
import com.example.domain.model.EpisodeModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf


data class EpisodesState(
    override val viewStatus: ViewStatus,
    val isLoadingMore: Boolean = false,
    val episodes: ImmutableList<EpisodeModel> = persistentListOf()
) : BaseState

sealed class EpisodesEvent : BaseEvent {
    data class LoadData(val at: Int = 1) : EpisodesEvent()
}

data object EpisodesEffect : BaseEffect