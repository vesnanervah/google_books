package com.example.google_books.model

import kotlinx.serialization.Serializable

@Serializable
data class VolumePrice(
    val amount: Float,
    val currencyCode: String,
)
