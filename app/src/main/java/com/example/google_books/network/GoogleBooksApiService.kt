package com.example.google_books.network

import com.example.google_books.model.Volume
import com.example.google_books.model.VolumeListResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface GoogleBooksApiService {

    @GET("volumes")
    suspend fun searchVolumes(@Query("q") searchString: String): VolumeListResponse

    @GET("volumes/")
    suspend fun searchVolume(@Url volumeId: Int): Volume
}