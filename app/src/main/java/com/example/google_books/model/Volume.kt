package com.example.google_books.model

import kotlinx.serialization.Serializable

@Serializable
data class Volume(
    val id: String,
    val volumeInfo: VolumeInfo,
    val saleInfo: VolumeSaleInfo,
)
