package com.example.effectiveacademy.repository.interfaces

import com.example.effectiveacademy.model.Superhero

interface INetworkRepository {
    suspend fun getCharacters(offset: Int): Result<List<Superhero>>
    suspend fun getCharacterById(id: Int): Result<Superhero>
}