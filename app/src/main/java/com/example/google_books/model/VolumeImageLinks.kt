package com.example.google_books.model

import kotlinx.serialization.Serializable

@Serializable
data class VolumeImageLinks(
    val thumbnail: String,
    val small: String? = null,
    val medium: String? = null,
    val large: String? = null,
)
