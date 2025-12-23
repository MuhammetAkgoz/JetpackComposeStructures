package com.example.coreui.builder

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.core.base.BaseState
import com.example.core.base.ViewStatus
import com.example.coreui.content.ErrorContent
import com.example.domain.model.asError

@Composable
fun ScreenStateBuilder(
    modifier: Modifier = Modifier,
    state: BaseState,
    onLoading: @Composable () -> Unit = {
        CircularProgressIndicator()
    },
    onRetry: (() -> Unit)? = null,
    onError: @Composable () -> Unit = {
        val error = state.error.asError()
        ErrorContent(
            type = error.errorType,
            title = error.title,
            message = error.message,
            code = error.errorCode,
            onRetry = onRetry
        )
    },
    onEmpty: @Composable () -> Unit = {
        // Default boş ekran
    },
    onSuccess: @Composable () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (state.viewStatus) {
            ViewStatus.INITIAL,
            ViewStatus.LOADING -> onLoading()

            ViewStatus.EMPTY -> onEmpty()

            ViewStatus.ERROR -> onError()

            ViewStatus.SUCCESS -> {
                onSuccess()
            }
        }
    }
}