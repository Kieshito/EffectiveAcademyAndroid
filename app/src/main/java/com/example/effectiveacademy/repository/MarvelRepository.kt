package com.example.effectiveacademy.repository

import com.example.effectiveacademy.model.MarvelCharacter
import com.example.effectiveacademy.network.NetworkModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MarvelRepository {
    private val api = NetworkModule.marvelApi

    suspend fun getCharacters(offset: Int): Result<List<MarvelCharacter>> = withContext(Dispatchers.IO) {
        runCatching {
            val timestamp = System.currentTimeMillis().toString()
            val hash = NetworkModule.generateHash(timestamp)
            val apiKey = NetworkModule.getApiKey()

            val response = api.getCharacters(
                apiKey = apiKey,
                timestamp = timestamp,
                hash = hash,
                limit = 5,
                offset = offset
            )
            response.data.results
        }
    }

    suspend fun getCharacter(id: Int): Result<MarvelCharacter> = withContext(Dispatchers.IO) {
        runCatching {
            val timestamp = System.currentTimeMillis().toString()
            val hash = NetworkModule.generateHash(timestamp)

            val response = api.getCharacter(
                characterId = id,
                apiKey = NetworkModule.getApiKey(),
                timestamp = timestamp,
                hash = hash
            )
            response.data.results.first()
        }
    }
}