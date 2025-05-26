package com.example.effectiveacademy.repository

import com.example.effectiveacademy.model.Superhero

class MarvelSuperheroRepository: ISuperheroRepository  {
    private val marvelRepository = MarvelRepository()

    override suspend fun getSuperHeroes(offset: Int): Result<List<Superhero>> {
        return marvelRepository.getCharacters(offset)
            .map { characters -> characters.map { MarvelMapper.toSuperHero(it) } }
    }

    override suspend fun findSuperHerobyId(id: Int): Result<Superhero> {
        return marvelRepository.getCharacter(id)
            .map { character -> MarvelMapper.toSuperHero(character) }
    }
}