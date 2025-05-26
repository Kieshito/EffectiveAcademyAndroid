package com.example.effectiveacademy.repository.interfaces

import com.example.effectiveacademy.model.Superhero
import kotlinx.coroutines.flow.Flow

interface ILocalRepository {
    fun getAllSuperheroes(): Flow<List<Superhero>>
    suspend fun getSuperheroById(id: Int): Superhero?
    suspend fun insertSuperheroes(superheroes: List<Superhero>)
    suspend fun insertSuperhero(superhero: Superhero)
}