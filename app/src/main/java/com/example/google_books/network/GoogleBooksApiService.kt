package com.example.google_books.network

import com.example.google_books.model.Volume
import com.example.google_books.model.VolumeListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GoogleBooksApiService {

    @GET("volumes")
    suspend fun searchVolumes(@Query("q") searchString: String, @Query("key") apiKey: String): VolumeListResponse

    @GET("volumes/{volumeId}")
    suspend fun searchVolume(@Path("volumeId") volumeId: String, @Query("key") apiKey: String): Volume
}