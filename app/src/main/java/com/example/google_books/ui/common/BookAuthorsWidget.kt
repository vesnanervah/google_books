package com.example.google_books.ui.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BookAuthorsWidget(authors: List<String?>?, modifier: Modifier = Modifier) {
    if (authors.isNullOrEmpty()) {
        return
    }
    Text(
        authors.joinToString(separator = ", "),
        modifier,
        style = MaterialTheme.typography.bodyMedium,
    )
}