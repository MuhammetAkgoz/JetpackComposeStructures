package com.example.presentation.screen.search

import com.example.core.base.BaseViewModel
import com.example.core.base.ViewStatus

class SearchViewModel : BaseViewModel<SearchState, SearchEvent, SearchEffect>() {
    override fun createInitialState(): SearchState {
        return SearchState(viewStatus = ViewStatus.INITIAL)
    }

    override fun consume(event: SearchEvent) {
        TODO("Not yet implemented")
    }

}