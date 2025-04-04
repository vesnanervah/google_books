package com.example.google_books.data

import com.example.google_books.network.GoogleBooksApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

interface AppContainer {
    val volumesRepository: VolumesRepository
}

class MockAppContainer: AppContainer {
    override val volumesRepository = MockVolumesRepository()
}

class DefaultAppContainer: AppContainer {
    private val baseUrl = "https://www.googleapis.com/books/v1/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory(MediaType.parse("application/json")!!))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: GoogleBooksApiService by lazy {
        retrofit.create(GoogleBooksApiService::class.java)
    }

    override val volumesRepository: NetworkVolumesRepository by lazy {
        NetworkVolumesRepository(retrofitService)
    }
}