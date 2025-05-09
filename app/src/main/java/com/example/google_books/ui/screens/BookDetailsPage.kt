package com.example.google_books.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
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
            Row(verticalAlignment = Alignment.Bottom) {
                Text(book.volumeInfo.title, style = MaterialTheme.typography.titleLarge,)
                if (book.volumeInfo.publishedDate != null) {
                    Text(book.volumeInfo.publishedDate, Modifier.padding(start = 10.dp, bottom = 3.dp), style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily.Cursive))
                }
            }
            BookAuthorsWidget(book.volumeInfo.authors, Modifier.padding(vertical = 4.dp))
            if (book.volumeInfo.averageRating != null) {
                BookCardSecondaryInfoText("Rating: ${book.volumeInfo.averageRating}")
            }
            if (book.volumeInfo.description != null) {
                BookCardSecondaryInfoText(book.volumeInfo.description)
            }
        }
    }
}

@Composable
private fun BookCardSecondaryInfoText(text: String) {
    Text(text, Modifier.padding(4.dp), style = MaterialTheme.typography.bodyMedium)
}
