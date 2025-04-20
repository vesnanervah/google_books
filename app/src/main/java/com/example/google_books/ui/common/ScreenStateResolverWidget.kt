package com.example.google_books.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.google_books.R
import com.example.google_books.ui.ScreenState

@Composable
fun ScreenStateResolverWidget(screenState: ScreenState, onRetryAction: () -> Unit, onSuccessChild: @Composable () -> Unit,) {
    when(screenState) {
        ScreenState.Error -> ErrorWidget { onRetryAction() }
        ScreenState.Loading -> ScreenStateLoadingIndicator ()
        ScreenState.Success -> onSuccessChild()
    }
}

// TODO: better loader view
@Composable
private fun ScreenStateLoadingIndicator() {
    Box(Modifier.background(Color.Black.copy(0.2F)).padding(8.dp)) {
        Image(painterResource(R.drawable.loading_img), "Loading", Modifier.size(160.dp))
    }
}

