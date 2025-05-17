package com.example.google_books.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.google_books.BooksApplication
import com.example.google_books.data.VolumesRepository
import com.example.google_books.model.Volume
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppViewModel(
    private val volumesRepository: VolumesRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState

     fun getBooksList(searchString: String) {
         loadData(
             {
                 _uiState.update {
                     it.copy(
                         searchString =  searchString,
                         booksListScreenState = ScreenState.Loading
                     )
                 }
             },
             {
                 val result: List<Volume> = volumesRepository.getVolumes(uiState.value.searchString!!)
                 _uiState.update {
                     it.copy(booksListScreenState = ScreenState.Success, booksList = result)
                 }
             },
             {
                 _uiState.update {
                     it.copy(booksListScreenState = ScreenState.Error, booksList = emptyList(),
                     )
                 }
             }
         )
    }

    fun selectBook(book: Volume) {
        _uiState.update { it.copy(bookDetails =  book) }
        getBookDetails(book.id)
    }

    fun getBookDetails(bookId: String) {
        loadData(
            {
                _uiState.update { it.copy( bookDetailsScreenState = ScreenState.Loading) }
            },
            {
                val result = volumesRepository.getVolumeDetailsById(bookId)
                _uiState.update {
                    it.copy(bookDetails = result, bookDetailsScreenState = ScreenState.Success)
                }
            },
            {
                _uiState.update { it.copy(bookDetailsScreenState = ScreenState.Error) }
            }
        )
    }

    fun resetSelectedBook() {
        _uiState.update {
            it.copy(bookDetails = null, bookDetailsScreenState = ScreenState.Success)
        }
    }

    private fun loadData(beforeLoadCb: () -> Unit, loadImpl: suspend () -> Unit, onErrorCb: () -> Unit) {
        viewModelScope.launch {
            beforeLoadCb()
            try {
                loadImpl()
            } catch (e: Exception) {
                onErrorCb()
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as BooksApplication
                AppViewModel(application.container.volumesRepository)
            }
        }
    }
}