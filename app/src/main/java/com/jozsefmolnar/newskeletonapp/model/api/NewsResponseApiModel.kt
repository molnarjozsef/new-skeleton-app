package com.jozsefmolnar.newskeletonapp.model.api

import kotlinx.serialization.Serializable

@Serializable
data class NewsResponseApiModel(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleApiModel>,
)
