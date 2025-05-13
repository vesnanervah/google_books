package com.example.google_books.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.google_books.ui.screens.BookDetailsPage
import com.example.google_books.ui.screens.BooksListPage
import com.example.google_books.ui.screens.SearchScreen

enum class GoogleBooksAppScreen {
    Search, BooksList, BooksDetails
}

@Composable
fun GoogleBooksApp(
    viewModel: AppViewModel = viewModel(factory = AppViewModel.Factory),
    navController: NavHostController = rememberNavController(),
    finishApplication: () -> Unit,
) {
    val backstackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = GoogleBooksAppScreen.valueOf(
        backstackEntry?.destination?.route ?: GoogleBooksAppScreen.Search.name
    )
    val uiState = viewModel.uiState.collectAsState()

    val appBarTitle = when (currentScreen) {
        GoogleBooksAppScreen.Search -> "What are we looking for?"
        GoogleBooksAppScreen.BooksList -> "Results: ${uiState.value.searchString}"
        else -> uiState.value.bookDetails?.volumeInfo?.title ?: "Details"
    }

    Scaffold(
        topBar = {
            GoogleBooksAppTopBar(
                appBarTitle,
                finishApplication,
                currentScreen != GoogleBooksAppScreen.Search,
                ) {
                val asArr = GoogleBooksAppScreen.entries.toTypedArray()
                val currentIndex = asArr.indexOf(currentScreen)
                val screen = asArr[currentIndex - 1].name
                navController.navigate(screen)
            }
        }
    ) {
        innerPadding -> Surface(Modifier.padding(innerPadding)) {
            NavHost(
                navController,
                currentScreen.name,
            ) {
                composable(GoogleBooksAppScreen.Search.name) {
                    SearchScreen(Modifier.fillMaxSize()) {
                        viewModel.getBooksList(it)
                        navController.navigate(GoogleBooksAppScreen.BooksList.name)
                    }
                }

                composable(GoogleBooksAppScreen.BooksList.name){
                    BooksListPage(
                        uiState.value.booksList,
                        uiState.value.booksListScreenState,
                        Modifier.fillMaxSize(),
                        { viewModel.getBooksList(uiState.value.searchString!!) }
                    ) {
                        viewModel.selectBook(it)
                        navController.navigate(GoogleBooksAppScreen.BooksDetails.name)
                    }
                }

                composable(GoogleBooksAppScreen.BooksDetails.name){
                    BookDetailsPage(uiState.value.bookDetails,
                        Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
                        uiState.value.bookDetailsScreenState) {
                        val id = uiState.value.bookDetails?.id
                        if (id!= null) {
                            viewModel.getBookDetails(id)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoogleBooksAppTopBar(
    title: String = "Sample text",
    finishApplication: () -> Unit,
    canNavigateUp: Boolean,
    navigateUp: () -> Unit,
) {
    BackHandler {
        if (canNavigateUp) {
            navigateUp()
        } else {
            finishApplication()
        }
    }
    TopAppBar(
        { Text(title, overflow = TextOverflow.Ellipsis, maxLines = 2) },
        navigationIcon = {
            if (canNavigateUp)
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                    )
                }
        }
    )
}

