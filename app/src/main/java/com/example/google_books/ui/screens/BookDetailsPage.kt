package com.example.google_books.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.google_books.data.MockVolumesRepository
import com.example.google_books.model.Volume
import com.example.google_books.ui.ScreenState
import com.example.google_books.ui.common.BookAuthorsWidget
import com.example.google_books.ui.common.ScreenStateResolverWidget

@Composable
fun BookDetailsPage(
    book: Volume?,
    screenState: ScreenState,
    onRetryAction: () -> Unit,

) {
    ScreenStateResolverWidget(
        screenState,
        onRetryAction
    ) {
        if (book == null) {
           Log.d("BooksDetailsPage", "Book is null, yet model didn't catch error")
        }
        Column(Modifier.padding(8.dp)) {
            if (book!!.volumeInfo.imageLinks.medium != null || book.volumeInfo.imageLinks.large != null) {
                AsyncImage(
                    model = book.volumeInfo.imageLinks.large ?: book.volumeInfo.imageLinks.medium,
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth().aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(Modifier.padding(8.dp))
            Card(Modifier.fillMaxSize()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(book.volumeInfo.title, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onPrimary)
                        Spacer(Modifier.padding(4.dp))
                        if (book.volumeInfo.publishedDate != null) {
                            Text(book.volumeInfo.publishedDate, style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily.Cursive), color = MaterialTheme.colorScheme.onPrimary)
                        }
                    }
                    BookAuthorsWidget(book.volumeInfo.authors, Modifier.padding(vertical = 4.dp))
                    if (book.volumeInfo.description != null) {
                        Text(book.volumeInfo.description, Modifier.padding(vertical = 4.dp), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onPrimary)
                    }

            }
        }
    }
}

@Preview
@Composable
fun BookDetailsPreview() {
    val book = MockVolumesRepository.mockDetailedVolume;
    BookDetailsPage(book, ScreenState.Success) { }
}