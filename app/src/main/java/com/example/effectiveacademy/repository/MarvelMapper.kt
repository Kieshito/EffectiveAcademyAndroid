package com.example.effectiveacademy.repository

import com.example.effectiveacademy.model.MarvelCharacter
import com.example.effectiveacademy.model.Superhero


object MarvelMapper {

    fun toSuperHero(marvelCharacter: MarvelCharacter): Superhero{
        return Superhero(
            heroId = marvelCharacter.id,
            name = marvelCharacter.name,
            description = marvelCharacter.description,
            image = "${marvelCharacter.thumbnail.path}.${marvelCharacter.thumbnail.extension}"
        )
    }

}