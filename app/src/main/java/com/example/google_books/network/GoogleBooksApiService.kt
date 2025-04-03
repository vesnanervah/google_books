package com.example.google_books.network

import com.example.google_books.model.Volume
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksApiService {

    @GET("volumes")
    suspend fun searchVolumes(@Query("q") searchString: String): List<Volume>

    @GET("volumes")
    suspend fun searchVolume(@Query("/") volumeId: Int): Volume
}