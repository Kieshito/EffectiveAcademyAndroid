package com.example.effectiveacademy.network

import com.example.effectiveacademy.model.MarvelResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path

interface MarvelApi {
    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("apikey") apiKey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): MarvelResponse

    @GET("v1/public/characters/{characterId}")
    suspend fun getCharacter(
        @Path("characterId") characterId: Int,
        @Query("apikey") apiKey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String
    ): MarvelResponse
}