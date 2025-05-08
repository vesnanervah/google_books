package com.example.google_books.model

import android.util.Log
import android.webkit.URLUtil
import kotlinx.serialization.Serializable

@Serializable
data class Volume(
    val id: String,
    val volumeInfo: VolumeInfo,
    val saleInfo: VolumeSaleInfo? = null,
) {

    init {
        replaceHttpImagesLinks(this)
    }

    companion object {
        private fun replaceHttpImagesLinks(volume: Volume) {
            // TODO: iterate through class fields with reflection
            val links = volume.volumeInfo.imageLinks
            links.thumbnail = replaceHttpLink(links.thumbnail)!!
            links.small = replaceHttpLink(links.small)
            links.medium = replaceHttpLink(links.medium)
            links.large = replaceHttpLink(links.large)

        }

        // OkHttp client in coil doesn't like http links, fetched from books api
        private fun replaceHttpLink(url: String?): String? {
            if (url == null || URLUtil.isHttpsUrl(url)) {
                return url
            }
            val result = url.split(Regex("^http")).joinToString("s")
            Log.d("http_convert", result)
            return url.split(Regex("^http")).joinToString("https")
        }
    }
}
