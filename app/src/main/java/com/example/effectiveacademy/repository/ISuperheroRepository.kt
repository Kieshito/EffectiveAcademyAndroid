package com.example.effectiveacademy.repository

import com.example.effectiveacademy.model.Superhero

interface ISuperheroRepository {
    suspend fun getSuperHeroes(): List<Superhero>
    suspend fun findSuperHerobyId(id: Int): Superhero
}