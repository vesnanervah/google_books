package com.example.google_books

import android.app.Application
import com.example.google_books.data.AppContainer
import com.example.google_books.data.DefaultAppContainer

class BooksApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}