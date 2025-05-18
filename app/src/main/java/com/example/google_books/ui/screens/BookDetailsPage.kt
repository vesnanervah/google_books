package com.example.google_books.ui.screens

import android.text.Html
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.google_books.R
import com.example.google_books.model.Volume
import com.example.google_books.ui.common.BookAuthorsWidget

@Composable
fun BookDetailsPage(
    book: Volume?,
    modifier: Modifier = Modifier,
) {
    Column(modifier.padding(8.dp)) {
        if (book!!.volumeInfo.imageLinks.medium != null || book.volumeInfo.imageLinks.large != null) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                AsyncImage(
                    model = book.volumeInfo.imageLinks.large
                        ?: book.volumeInfo.imageLinks.medium,
                    contentDescription = "",
                    placeholder = painterResource(R.drawable.loading_img),
                    modifier = Modifier
                        .height(340.dp),
                    contentScale = ContentScale.FillHeight,)
            }
        }
        Text(book.volumeInfo.title, Modifier.padding(top = 16.dp), style = MaterialTheme.typography.bodyLarge,)
        if (book.volumeInfo.publishedDate != null){
            BookCardSecondaryInfoRow(
                "Release date: ${book.volumeInfo.publishedDate}",
                Icons.Default.DateRange,)
        }
        BookAuthorsWidget(
            book.volumeInfo.authors,
            Modifier.padding(top = 8.dp),
            "Authors: ",
            Icons.Default.Person,
            )
        if (book.volumeInfo.averageRating != null) {
            BookCardSecondaryInfoRow(
                "Rating: ${book.volumeInfo.averageRating}",
                Icons.Default.Star,)
        }
        if (book.volumeInfo.description != null) {
            BookCardSecondaryInfoRow(removeTagsFromString(book.volumeInfo.description))
        }
    }
}

@Composable
private fun BookCardSecondaryInfoRow(text: String, icon: ImageVector? = null) {
    if (icon != null) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, "", Modifier.padding(top = 4.dp ,end = 4.dp).size(18.dp))
            Text(text, Modifier.padding(top = 8.dp), style = MaterialTheme.typography.bodyMedium)
        }
    } else {
        Text(text, Modifier.padding(top = 8.dp), style = MaterialTheme.typography.bodyMedium)
    }
}

private fun removeTagsFromString(text: String): String {
    return Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT).toString()
}
