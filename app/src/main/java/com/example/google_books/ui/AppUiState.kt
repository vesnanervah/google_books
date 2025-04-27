package com.example.google_books.ui

import com.example.google_books.model.Volume

data class AppUiState(
    val searchString: String?,
    val bookDetails: Volume? = null,
    var booksListScreenState: ScreenState = ScreenState.Loading,
    var bookDetailsScreenState: ScreenState = ScreenState.Loading,
    var booksList: List<Volume> = emptyList(),
)


enum class ScreenState {
    Error,
    Success,
    Loading
}


