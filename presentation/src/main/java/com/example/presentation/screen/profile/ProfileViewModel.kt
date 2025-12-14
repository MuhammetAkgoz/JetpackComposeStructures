package com.example.presentation.screen.profile
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class ProfileViewModel : ViewModel() {
    private val _stateFlow = MutableStateFlow(ProfileState(state = 0))
    val state: StateFlow<ProfileState> = _stateFlow
}