package com.example.presentation.screen.home

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.functional.cross
import com.example.domain.usecase.EmailListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private  val useCase: EmailListUseCase
) : ViewModel() {
    private val _stateFlow = MutableStateFlow(HomeState(Color.Black))
    val state: StateFlow<HomeState> = _stateFlow

    fun setTitle(title: String?) {
        _stateFlow.update { it.copy(title = title) }
    }


    public  fun getEmails() {
        viewModelScope.launch {
            useCase.invoke().cross(
                right = { list ->
                    list
                    print(list[0].from)
                    print(list[0].id)
                    print(list[0].date)
                    print(list[0].subject)
                },
                left = { error ->
                    error
                },

            )
        }
    }
}