package com.example.effectiveacademy.model

import com.squareup.moshi.JsonClass

/*
Смотреть сюда:
https://developer.marvel.com/documentation/apiresults
https://developer.marvel.com/documentation/entity_types
https://developer.marvel.com/documentation/images
 */

@JsonClass(generateAdapter = true)
data class MarvelResponse(
        val code: Int,
        val status: String,
        val data: MarvelData
)

@JsonClass(generateAdapter = true)
data class MarvelData(
        val results: List<MarvelCharacter>
)

@JsonClass(generateAdapter = true)
data class MarvelCharacter(
        val id: Int,
        val name: String,
        val description: String,
        val thumbnail: Thumbnail
)

@JsonClass(generateAdapter = true)
data class Thumbnail(
        val path: String,
        val extension: String
)

