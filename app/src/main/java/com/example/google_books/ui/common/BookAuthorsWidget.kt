package com.example.google_books.ui.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BookAuthorsWidget(authors: List<String?>?, modifier: Modifier = Modifier, color: Color = MaterialTheme.colorScheme.onPrimary) {
    if (authors.isNullOrEmpty()) {
        return
    }
    Text(
        authors.joinToString(separator = ", "),
        modifier,
        style = MaterialTheme.typography.bodyMedium,
        color = color,
    )
}