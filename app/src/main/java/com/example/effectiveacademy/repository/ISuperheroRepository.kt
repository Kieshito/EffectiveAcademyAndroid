package com.example.effectiveacademy.repository

import com.example.effectiveacademy.model.Superhero

interface ISuperheroRepository {
    suspend fun getSuperHeroes(offset: Int = 0): List<Superhero>
    suspend fun findSuperHerobyId(id: Int): Superhero
}