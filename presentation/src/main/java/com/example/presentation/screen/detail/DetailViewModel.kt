package com.example.presentation.screen.detail

import com.example.core.base.BaseViewModel


class DetailViewModel : BaseViewModel<DetailState, DetailEvent, DetailEffect>() {

    override fun createInitialState(): DetailState {
        return DetailState()
    }

    override fun consume(event: DetailEvent) {
    }

}