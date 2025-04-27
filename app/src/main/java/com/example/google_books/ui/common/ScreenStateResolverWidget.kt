package com.example.google_books.ui.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.google_books.ui.ScreenState

@Composable
fun ScreenStateResolverWidget(screenState: ScreenState, onRetryAction: () -> Unit, onSuccessChild: @Composable () -> Unit,) {
    when(screenState) {
        ScreenState.Error -> ErrorWidget(modifier = Modifier.fillMaxSize()) { onRetryAction() }
        ScreenState.Loading -> ScreenStateLoadingIndicator ()
        ScreenState.Success -> onSuccessChild()
    }
}

@Composable
private fun ScreenStateLoadingIndicator() {
    CircularProgressIndicator(Modifier.fillMaxSize().wrapContentSize(Alignment.Center))
}

