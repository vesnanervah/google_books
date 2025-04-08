package com.example.google_books

import android.app.Application
import com.example.google_books.data.AppContainer
import com.example.google_books.data.MockAppContainer

class GoogleBooksApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        // TODO: replace with network container after ui complete
        container = MockAppContainer()
    }
}