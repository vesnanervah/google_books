package com.example.google_books.ui.common

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.google_books.ui.ScreenState

@Composable
fun ScreenStateResolverWidget(screenState: ScreenState, modifier: Modifier = Modifier, onRetryAction: () -> Unit, onSuccessChild: @Composable () -> Unit,) {
    when(screenState) {
        ScreenState.Error -> ErrorWidget(modifier = modifier) { onRetryAction() }
        ScreenState.Loading -> ScreenStateLoadingIndicator (modifier)
        ScreenState.Success -> onSuccessChild()
    }
}

@Composable
private fun ScreenStateLoadingIndicator(modifier: Modifier = Modifier) {
    CircularProgressIndicator(modifier.wrapContentSize(Alignment.Center))
}

