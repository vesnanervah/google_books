package com.example.google_books.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun BookAuthorsWidget(
    authors: List<String?>?,
    modifier: Modifier = Modifier,
    prefix: String? = null,
    icon: ImageVector? = null,
) {
    if (authors.isNullOrEmpty()) {
        return
    }
    if (icon != null) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, "", Modifier.padding(top = 4.dp ,end = 4.dp).size(18.dp))
            Text(
                (prefix ?: "") + authors.joinToString(separator = ", "),
                modifier,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    } else {
        Text(
            (prefix ?: "") + authors.joinToString(separator = ", "),
            modifier,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}