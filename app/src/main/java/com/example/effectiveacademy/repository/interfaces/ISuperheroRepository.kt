package com.example.effectiveacademy.repository.interfaces

import com.example.effectiveacademy.model.Superhero

interface ISuperheroRepository {
    suspend fun getSuperHeroes(offset: Int = 0): Result<List<Superhero>>
    suspend fun findSuperHerobyId(id: Int): Result<Superhero>
}