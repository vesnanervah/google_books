package com.example.google_books.model

import kotlinx.serialization.Serializable

@Serializable
data class VolumeInfo(
    val title: String,
    val authors: List<String>,
    val publisher: String,
    val publishedDate: String,
    val description: String,
    val imageLinks: VolumeImageLinks,
    )
