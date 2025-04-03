package com.example.google_books.model

import kotlinx.serialization.Serializable

@Serializable
data class VolumeImageLinks(
    val thumbnail: String,
    val small: String,
    val medium: String,
    val large: String,
)
