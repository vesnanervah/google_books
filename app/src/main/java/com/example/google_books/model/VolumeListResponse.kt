package com.example.google_books.model

import kotlinx.serialization.Serializable

@Serializable
data class VolumeListResponse(
    val kind: String,
    val items: List<Volume>,
    val totalCount: Int,
)
