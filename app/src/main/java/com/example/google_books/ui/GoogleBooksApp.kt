package com.example.google_books.ui

import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

enum class GoogleBooksAppScreen {
    BooksList, BooksDetails
}

@Composable
fun GoogleBooksApp() {
    val navController = rememberNavController()
    val backstackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = GoogleBooksAppScreen.valueOf(
        backstackEntry?.destination?.route ?: GoogleBooksAppScreen.BooksList.name
    )

    val viewModel: AppViewModel = viewModel()

    Scaffold(
        topBar = {
            GoogleBooksAppTopBar(canNavigateBack = currentScreen == GoogleBooksAppScreen.BooksDetails) {

            }
        }
    ) {
        innerPadding -> Surface(Modifier.padding(innerPadding)) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoogleBooksAppTopBar(
    title: String = "Sample text",
    canNavigateBack: Boolean,
    navigateUp: () -> Unit
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            if (canNavigateBack)
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                    )
                }
        }
    )
}

