package com.example.google_books.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.google_books.data.MockVolumesRepository
import com.example.google_books.model.Volume
import com.example.google_books.model.VolumeListResponse
import kotlinx.coroutines.runBlocking

@Composable
fun BooksListPage(books: List<Volume>, modifier: Modifier = Modifier, ) {
    LazyVerticalGrid(GridCells.Adaptive(150.dp)) {
        items(books) {
            BooksListItem(it)
        }
    }
}

@Composable
private fun BooksListItem(book: Volume, modifier: Modifier = Modifier) {
    Card {
        Box {
            // TODO: Coil image
            Column {
                Text(book.volumeInfo.title)
                Spacer(Modifier.padding(8.dp))
                if(book.volumeInfo.authors?.isNotEmpty() == true)
                Text(book.volumeInfo.authors.joinToString(", "))
            }
        }
    }
}

@Preview
@Composable
private fun BooksListItemPreview() {
    runBlocking {
        val book = MockVolumesRepository().getVolumes("").first();
        BooksListItem(book)
    }
}

