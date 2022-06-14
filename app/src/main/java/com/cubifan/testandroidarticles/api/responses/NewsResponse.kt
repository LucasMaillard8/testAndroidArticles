package com.cubifan.testandroidarticles.api.responses

data class NewsResponse(
    val data: NewsDataResponse
)

data class NewsDataResponse(
    val items: Array<Article>,
    val currentItemCount: Int
)

data class Article(
    val title: String,
    val published: String,
    val medias: Medias
)

data class Medias(
    val imageCount: Int,
    val images: Array<ImageData>
)

data class ImageData(
    val original: OriginalData
)

data class OriginalData(
    val url: String
)