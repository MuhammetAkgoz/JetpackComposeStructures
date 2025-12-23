package com.example.presentation.screen.profile
import com.example.core.base.BaseViewModel
import com.example.core.base.ViewStatus


class ProfileViewModel : BaseViewModel<ProfileState, ProfileEvent, ProfileEffect>() {
    override fun createInitialState(): ProfileState {
        return ProfileState(ViewStatus.INITIAL)
    }

    override fun consume(event: ProfileEvent) {
        TODO("Not yet implemented")
    }
}