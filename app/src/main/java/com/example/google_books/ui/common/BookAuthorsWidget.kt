package com.example.google_books.ui.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BookAuthorsWidget(authors: List<String?>?, color: Color = MaterialTheme.colorScheme.onPrimary) {
    if (authors.isNullOrEmpty()) {
        return
    }
    Text(
        authors.joinToString(separator = ", "),
        style = MaterialTheme.typography.bodyMedium,
        color = color,
    )
}