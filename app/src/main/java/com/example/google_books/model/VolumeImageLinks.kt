package com.example.google_books.model

import kotlinx.serialization.Serializable

@Serializable
data class VolumeImageLinks(
    var thumbnail: String,
    var small: String? = null,
    var medium: String? = null,
    var large: String? = null,
)
