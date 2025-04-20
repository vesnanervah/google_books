package com.example.google_books.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.google_books.R
import com.example.google_books.model.Volume
import com.example.google_books.ui.ScreenState
import com.example.google_books.ui.common.ScreenStateResolverWidget

@Composable
fun BooksListPage(
        books: List<Volume>,
        screenState: ScreenState,
        modifier: Modifier = Modifier,
        onLoadRetry: () -> Unit,
        onBookTap: (Volume) -> Unit,
        ) {
    ScreenStateResolverWidget(
        screenState,
        onLoadRetry,
    ) {
        if (books.isEmpty()) {
            Text("There is no books")
        } else {
            LazyVerticalGrid(GridCells.Adaptive(150.dp)) {
                items(books) {
                    BooksListItem(it, Modifier.clickable {
                        onBookTap(it)
                    })
                }
            }
        }
    }
}

@Composable
private fun BooksListItem(book: Volume, modifier: Modifier = Modifier) {
    Card {
        Box {
            AsyncImage(
                model = book.volumeInfo.imageLinks.thumbnail,
                contentDescription = "",
                placeholder = painterResource(R.drawable.loading_img),
                modifier = Modifier.fillMaxSize(),
            )
            Column(Modifier.background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5F)).padding(8.dp)) {
                Text(book.volumeInfo.title)
                Spacer(Modifier.padding(8.dp))
                if(book.volumeInfo.authors?.isNotEmpty() == true)
                Text(book.volumeInfo.authors.joinToString(", "))
            }
        }
    }
}

