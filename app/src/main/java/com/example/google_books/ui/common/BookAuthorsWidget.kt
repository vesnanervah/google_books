package com.example.google_books.ui.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BookAuthorsWidget(authors: List<String?>?, modifier: Modifier = Modifier, prefix: String? = null) {
    if (authors.isNullOrEmpty()) {
        return
    }
    Text(
        (prefix ?: "") + authors.joinToString(separator = ", "),
        modifier,
        style = MaterialTheme.typography.bodyMedium,
    )
}