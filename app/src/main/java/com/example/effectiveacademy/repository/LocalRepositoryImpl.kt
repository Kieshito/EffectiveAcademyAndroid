package com.example.effectiveacademy.repository

import android.content.Context
import com.example.effectiveacademy.data.AppDataBase
import com.example.effectiveacademy.model.Superhero
import com.example.effectiveacademy.repository.interfaces.ILocalRepository
import com.example.effectiveacademy.repository.mapper.SuperheroMapper.toSuperhero
import com.example.effectiveacademy.repository.mapper.SuperheroMapper.toEntity

class LocalRepositoryImpl(context: Context) : ILocalRepository {
    private val database = AppDataBase.getDatabase(context)
    private val characterDao = database.superheroDao()

    override suspend fun getSuperheroById(id: Int): Superhero? {
        val entity = characterDao.getCharacterById(id)
        return entity?.toSuperhero()
    }

    override suspend fun insertSuperheroes(superheroes: List<Superhero>) {
        characterDao.insertCharacters(superheroes.map { it.toEntity() })
    }

    override suspend fun insertSuperhero(superhero: Superhero) {
        characterDao.insertCharacter(superhero.toEntity())
    }
} 