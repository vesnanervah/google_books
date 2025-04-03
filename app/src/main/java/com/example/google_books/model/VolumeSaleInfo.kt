package com.example.google_books.model

import kotlinx.serialization.Serializable

@Serializable
data class VolumeSaleInfo(
    val listPrice: VolumePrice? = null,
    val retailPrice: VolumePrice? = null,
)
