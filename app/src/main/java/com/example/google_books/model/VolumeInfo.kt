package com.example.google_books.model

import kotlinx.serialization.Serializable

@Serializable
data class VolumeInfo(
    val title: String,
    val authors: List<String>? = null,
    val publisher: String? = null,
    val publishedDate: String? = null,
    val description: String? = null,
    val imageLinks: VolumeImageLinks,
    val pageCount: Int? = null,
    val averageRating: Double? = null,
    val mainCategory: String? = null,
    val categories: List<String>? = null
    )
