package com.example.google_books.data

import com.example.google_books.model.Volume
import com.example.google_books.model.VolumeImageLinks
import com.example.google_books.model.VolumeInfo
import com.example.google_books.model.VolumePrice
import com.example.google_books.model.VolumeSaleInfo
import com.example.google_books.network.GoogleBooksApiService
import kotlinx.coroutines.delay

interface VolumesRepository {
    suspend fun getVolumes(searchQuery: String): List<Volume>

    suspend fun getVolumeDetailsById(volumeId: String): Volume
}

class NetworkVolumesRepository(private val apiService: GoogleBooksApiService): VolumesRepository {
    override suspend fun getVolumes(searchQuery: String): List<Volume> {
        TODO("Not yet implemented")
    }

    override suspend fun getVolumeDetailsById(volumeId: String): Volume {
        TODO("Not yet implemented")
    }


}

class MockVolumesRepository: VolumesRepository {
    override suspend fun getVolumes(searchQuery: String): List<Volume> {
        delay(5000)
        return listOf(
            mockListItemVolume,
            mockListItemVolume,
            mockListItemVolume,
            mockListItemVolume,
            mockListItemVolume,
            mockListItemVolume,
            mockListItemVolume,
            mockListItemVolume,
        )
    }

    override suspend fun getVolumeDetailsById(volumeId: String): Volume {
        delay(5000)
        return mockDetailedVolume
    }

    companion object {
        val mockListItemVolume = Volume(
            "_ojXNuzgHRcC",
            VolumeInfo(
                "Flowers",
                listOf("Vijaya Khisty Bodach"),
                imageLinks = VolumeImageLinks(
                    thumbnail = "https://books.google.com/books?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
                )
            )
        )

        val mockDetailedVolume = Volume(
            "_ojXNuzgHRcC",
            VolumeInfo(
                "Flowers",
                listOf("Vijaya Khisty Bodach"),
                "Random House Digital, Inc.",
                "2005-11-15",
                """"\"Here is the story behind one of the most remarkable Internet
                        successes of our time. Based on scrupulous research and extraordinary access
                        to Google, ...
                        """",
                VolumeImageLinks(
                    "https://books.google.com/books?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
                    "https://books.google.com/books?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=2&edge=curl&source=gbs_api",
                    "https://books.google.com/books?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=3&edge=curl&source=gbs_api",
                    "https://books.google.com/books?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=4&edge=curl&source=gbs_api",
                ),
                207,
                3.5,
                "Business & Economics / Entrepreneurship",
            ),
            VolumeSaleInfo(
                VolumePrice(11.99, "USD"),
                VolumePrice(11.99, "USD")
            )
        )
    }
}

