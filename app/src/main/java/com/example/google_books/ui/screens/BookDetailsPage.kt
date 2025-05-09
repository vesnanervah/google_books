package com.example.google_books.ui.screens

import android.text.Html
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.google_books.R
import com.example.google_books.model.Volume
import com.example.google_books.ui.ScreenState
import com.example.google_books.ui.common.BookAuthorsWidget
import com.example.google_books.ui.common.ScreenStateResolverWidget

@Composable
fun BookDetailsPage(
    book: Volume?,
    modifier: Modifier = Modifier,
    screenState: ScreenState,
    onRetryAction: () -> Unit,

) {
    ScreenStateResolverWidget(
        screenState,
        onRetryAction
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
                        contentScale = ContentScale.FillHeight,
                    )
                }
            }
            Text(book.volumeInfo.title, Modifier.padding(top = 8.dp), style = MaterialTheme.typography.bodyLarge,)
            if (book.volumeInfo.publishedDate != null){
                BookCardSecondaryInfoRow(
                    "Release date: ${book.volumeInfo.publishedDate}",
                )
            }
            BookAuthorsWidget(book.volumeInfo.authors, Modifier.padding(top = 8.dp), "Authors: ")
            if (book.volumeInfo.averageRating != null) {
                BookCardSecondaryInfoRow("Rating: ${book.volumeInfo.averageRating}")
            }
            if (book.volumeInfo.description != null) {
                BookCardSecondaryInfoRow(removeTagsFromString(book.volumeInfo.description))
            }
        }
    }
}

@Composable
private fun BookCardSecondaryInfoRow(text: String) {
    Text(text, Modifier.padding(top = 8.dp), style = MaterialTheme.typography.bodyMedium)
}

private fun removeTagsFromString(text: String): String {
    return Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT).toString()
}
