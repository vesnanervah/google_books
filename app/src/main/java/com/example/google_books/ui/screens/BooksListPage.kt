package com.example.google_books.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.google_books.R
import com.example.google_books.data.MockVolumesRepository
import com.example.google_books.model.Volume
import com.example.google_books.ui.ScreenState
import com.example.google_books.ui.common.BookAuthorsWidget

@Composable
fun BooksListPage(
    books: List<Volume>,
    modifier: Modifier = Modifier,
    onBookTap: (Volume) -> Unit,
    ) {
    if (books.isEmpty()) {
        Text("There is no books")
    } else {
        LazyVerticalGrid(GridCells.Adaptive(150.dp), modifier) {
            items(books) {
                BooksListItem(it, Modifier.height(200.dp), onBookTap)
            }
        }
    }
}

@Composable
private fun BooksListItem(book: Volume, modifier: Modifier = Modifier, onBookTap: (Volume) -> Unit,) {
    Card(modifier
        .padding(8.dp)
        .fillMaxSize()
        .clickable { onBookTap(book) }) {
        Box {
            AsyncImage(
                model = book.volumeInfo.imageLinks.thumbnail,
                error = painterResource(R.drawable.placeholder),
                contentDescription = "",
                placeholder = painterResource(R.drawable.loading_img),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background.copy(0.65F)).padding(8.dp)) {
                Text(
                    book.volumeInfo.title,
                    Modifier.weight(1F, fill = false),
                    style = MaterialTheme.typography.bodyLarge,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.padding(top = 4.dp))
                BookAuthorsWidget(book.volumeInfo.authors)
            }
        }
    }
}

