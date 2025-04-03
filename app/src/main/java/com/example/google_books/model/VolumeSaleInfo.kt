package com.example.google_books.model

import kotlinx.serialization.Serializable

@Serializable
data class VolumeSaleInfo(
    val listPrice: VolumePrice,
    val retailPrice: VolumePrice,
)
