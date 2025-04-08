package com.example.google_books.ui

import com.example.google_books.model.Volume

data class AppUiState(
    val selectedVolume: Volume? = null,
    var booksListScreenState: ScreenState = ScreenState.Loading,
    var bookDetailsScreenState: ScreenState = ScreenState.Loading,
)


enum class ScreenState {
    Error,
    Success,
    Loading
}


