package com.example.effectiveacademy.repository

import com.example.effectiveacademy.model.MarvelCharacter
import com.example.effectiveacademy.model.Superhero


object MarvelMapper {

    fun toSuperHero(marvelCharacter: MarvelCharacter): Superhero {
        val image = if (marvelCharacter.thumbnail.path == "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available") {
            "https://foni.papik.pro/uploads/posts/2024-09/foni-papik-pro-d1ls-p-kartinki-am-nyam-na-prozrachnom-fone-20.png"
        } else {
            "${marvelCharacter.thumbnail.path}.${marvelCharacter.thumbnail.extension}"
        }
        
        return Superhero(
            heroId = marvelCharacter.id,
            name = marvelCharacter.name,
            description = marvelCharacter.description,
            image = image
        )
    }
}