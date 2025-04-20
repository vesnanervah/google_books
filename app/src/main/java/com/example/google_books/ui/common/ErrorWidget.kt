package com.example.google_books.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ErrorWidget(text: String = "Something went wrong", modifier: Modifier = Modifier, onRetry: () -> Unit) {
    Column(Modifier, Arrangement.Center, Alignment.CenterHorizontally) {
        Text(text)
        Spacer(Modifier.padding(vertical = 8.dp))
        Button(
            onClick = onRetry
        ) {
            Text("Retry")
        }

    }
}