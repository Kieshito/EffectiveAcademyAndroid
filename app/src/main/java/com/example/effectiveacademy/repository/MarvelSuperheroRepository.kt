package com.example.effectiveacademy.repository

import com.example.effectiveacademy.model.Superhero

class MarvelSuperheroRepository: ISuperheroRepository {
    private val marvelRepository = MarvelRepository()

    override suspend fun getSuperHeroes(): List<Superhero> {
        return marvelRepository.getCharacters()
            .getOrThrow()
            .map { MarvelMapper.toSuperHero(it)}
            ?: emptyList()
    }

    override suspend fun findSuperHerobyId(id: Int): Superhero {
        return marvelRepository.getCharacter(id)
            .getOrThrow()
            .let { MarvelMapper.toSuperHero(it) }
            ?: throw Exception("Hero not found")
    }

}