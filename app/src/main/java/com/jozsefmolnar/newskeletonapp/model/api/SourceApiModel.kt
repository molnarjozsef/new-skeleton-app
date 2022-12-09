package com.jozsefmolnar.newskeletonapp.model.api

import kotlinx.serialization.Serializable

@Serializable
data class SourceApiModel(
	val id: String?,
	val name: String,
)
