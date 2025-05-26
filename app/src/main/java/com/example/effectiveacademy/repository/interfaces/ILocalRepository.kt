package com.example.effectiveacademy.repository.interfaces

import com.example.effectiveacademy.model.Superhero

interface ILocalRepository {
    suspend fun getSuperheroById(id: Int): Superhero?
    suspend fun insertSuperheroes(superheroes: List<Superhero>)
    suspend fun insertSuperhero(superhero: Superhero)
}