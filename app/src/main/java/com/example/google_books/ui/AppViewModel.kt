package com.example.google_books.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.google_books.GoogleBooksApplication
import com.example.google_books.data.MockVolumesRepository
import com.example.google_books.data.VolumesRepository
import com.example.google_books.model.Volume
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppViewModel(): ViewModel() {
    private val volumesRepository: VolumesRepository = MockVolumesRepository()
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState

    init {
        getBooksList()
    }

//
//    // TODO: pass query string
     fun getBooksList() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    booksListScreenState = ScreenState.Loading
                )
            }
            try {
                val result: List<Volume> = volumesRepository.getVolumes("");
                _uiState.update {
                    it.copy(
                        booksListScreenState = ScreenState.Success,
                        booksList = result
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        booksListScreenState = ScreenState.Error,
                        booksList = emptyList(),
                    )
                }
            }
        }
    }
//
//
//    companion object {
//        val Factory: ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                val application = (this[APPLICATION_KEY] as GoogleBooksApplication)
//                AppViewModel(application.container.volumesRepository)
//            }
//        }
//    }
}