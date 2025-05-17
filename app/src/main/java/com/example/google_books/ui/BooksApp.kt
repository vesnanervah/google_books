package com.example.google_books.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.google_books.ui.common.ScreenStateResolverWidget
import com.example.google_books.ui.screens.BookDetailsPage
import com.example.google_books.ui.screens.BooksListPage
import com.example.google_books.ui.screens.SearchScreen

enum class BooksCompactAppScreen {
    Search, BooksList, BooksDetails
}

enum class BooksExpandedAppScreen {
    Search, ListAndDetails
}

@Composable
fun BooksApp(
    windowSize: WindowWidthSizeClass,
    viewModel: AppViewModel = viewModel(factory = AppViewModel.Factory),
    finishApplication: () -> Unit,
) {
    when (windowSize) {
        WindowWidthSizeClass.Expanded -> BooksAppExpandedLayout(viewModel){ finishApplication() }
        else -> BooksAppCompactLayout(viewModel){ finishApplication() }
    }
}

@Composable
fun AppBasicLayout(
        title: String = "Sample text",
        finishApplication: () -> Unit,
        canNavigateUp: Boolean,
        navigateUp: () -> Unit,
        navHostController: NavHostController,
        startDestination: String,
        navItems: NavGraphBuilder.() -> Unit
    ) {
    Scaffold(
        topBar = { GoogleBooksAppTopBar(title, finishApplication, canNavigateUp, navigateUp) }
    ) {
        Surface(Modifier.padding(it)) {
            NavHost(navHostController, startDestination) {
                navItems()
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

@Composable
fun BooksAppCompactLayout(
    viewModel: AppViewModel,
    navController: NavHostController = rememberNavController(),
    finishApplication: () -> Unit,
) {
    val backstackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = BooksCompactAppScreen.valueOf(
        backstackEntry?.destination?.route ?: BooksCompactAppScreen.Search.name
    )
    val uiState = viewModel.uiState.collectAsState()

    val appBarTitle = when (currentScreen) {
        BooksCompactAppScreen.Search -> "What are we looking for?"
        BooksCompactAppScreen.BooksList -> "Results: ${uiState.value.searchString}"
        else -> uiState.value.bookDetails?.volumeInfo?.title ?: "Details"
    }
    val canNavigateUp = currentScreen != BooksCompactAppScreen.Search

    AppBasicLayout(
        appBarTitle,
        finishApplication,
        canNavigateUp,
        {
            val asArr = BooksCompactAppScreen.entries.toTypedArray()
            val currentIndex = asArr.indexOf(currentScreen)
            val screen = asArr[currentIndex - 1].name
            navController.navigate(screen)
        },
        navController,
        currentScreen.name
    ) {
        composable(BooksCompactAppScreen.Search.name) {
            SearchScreen(Modifier.fillMaxSize()) {
                viewModel.getBooksList(it)
                navController.navigate(BooksCompactAppScreen.BooksList.name)
            }
        }

        composable(BooksCompactAppScreen.BooksList.name) {
            ScreenStateResolverWidget(
                uiState.value.booksListScreenState,
                Modifier.fillMaxSize(),
                { viewModel.getBooksList(uiState.value.searchString!!) }
            ) {
                BooksListPage(
                    uiState.value.booksList,
                    Modifier.fillMaxSize(),
                ) {
                    viewModel.selectBook(it)
                    navController.navigate(BooksCompactAppScreen.BooksDetails.name)
                }
            }
        }

        composable(BooksCompactAppScreen.BooksDetails.name) {
            ScreenStateResolverWidget(
                uiState.value.bookDetailsScreenState,
                Modifier.fillMaxSize(),
                {
                    val id = uiState.value.bookDetails?.id
                    if (id!= null) {
                        viewModel.getBookDetails(id)
                    }
                }
            ) {
                BookDetailsPage(
                    uiState.value.bookDetails,
                    Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
                )
            }
        }
    }
}

@Composable
fun BooksAppExpandedLayout(
    viewModel: AppViewModel,
    navController: NavHostController = rememberNavController(),
    finishApplication: () -> Unit,
) {
    val backstackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = BooksExpandedAppScreen.valueOf(
        backstackEntry?.destination?.route ?: BooksExpandedAppScreen.Search.name
    )
    val uiState = viewModel.uiState.collectAsState()

    val appBarTitle = when (currentScreen) {
        BooksExpandedAppScreen.Search -> "What are we looking for?"
        else -> "Results: ${uiState.value.searchString}"
    }
    val canNavigateUp = currentScreen != BooksExpandedAppScreen.Search


    AppBasicLayout(
        appBarTitle,
        finishApplication,
        canNavigateUp,
        {
            if (uiState.value.bookDetails != null) {
                viewModel.resetSelectedBook()
            } else {
                navController.navigate(BooksExpandedAppScreen.Search.name)
            }
        },
        navController,
        currentScreen.name
    ) {
        composable(BooksExpandedAppScreen.Search.name) {
            SearchScreen(Modifier.fillMaxSize()) {
                viewModel.getBooksList(it)
                navController.navigate(BooksExpandedAppScreen.ListAndDetails.name)
            }
        }
        composable(BooksExpandedAppScreen.ListAndDetails.name) {
            ScreenStateResolverWidget(
                uiState.value.booksListScreenState,
                Modifier.fillMaxSize(),
                { viewModel.getBooksList(uiState.value.searchString!!) }
            ) {
                Row {
                    BooksListPage(
                        uiState.value.booksList,
                        Modifier.weight(1F),
                    ) { viewModel.selectBook(it) }
                    if (uiState.value.bookDetails != null) {
                        ScreenStateResolverWidget(
                            uiState.value.bookDetailsScreenState,
                            Modifier.weight(3F).fillMaxHeight(),
                            {
                                val id = uiState.value.bookDetails?.id
                                if (id!= null) {
                                    viewModel.getBookDetails(id)
                                }
                            } ){
                            BookDetailsPage(uiState.value.bookDetails, Modifier.weight(3F).verticalScroll(rememberScrollState()))
                        }
                    }
                }
            }
        }
    }
}

