package com.example.effectiveacademy.repository

import com.example.effectiveacademy.repository.mapper.SuperheroMapper.toSuperhero
import com.example.effectiveacademy.model.Superhero
import com.example.effectiveacademy.network.NetworkModule
import com.example.effectiveacademy.repository.interfaces.INetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkRepositoryImpl : INetworkRepository {
    private val api = NetworkModule.marvelApi

    override suspend fun getCharacters(offset: Int): Result<List<Superhero>> = withContext(Dispatchers.IO) {
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

            response.data.results.map { it.toSuperhero() }
        }
    }

    override suspend fun getCharacterById(id: Int): Result<Superhero> = withContext(Dispatchers.IO) {
        runCatching {
            val timestamp = System.currentTimeMillis().toString()
            val hash = NetworkModule.generateHash(timestamp)

            val response = api.getCharacter(
                characterId = id,
                apiKey = NetworkModule.getApiKey(),
                timestamp = timestamp,
                hash = hash
            )

            response.data.results.first().toSuperhero()
        }
    }
} 