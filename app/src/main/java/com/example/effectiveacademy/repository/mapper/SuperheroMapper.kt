package com.example.effectiveacademy.repository.mapper

import com.example.effectiveacademy.data.SuperheroEntity
import com.example.effectiveacademy.model.MarvelCharacter
import com.example.effectiveacademy.model.Superhero

object SuperheroMapper {

    //CharacterDTO → CharacterEntity
    fun MarvelCharacter.toEntity(): SuperheroEntity {
        return SuperheroEntity(
            id = id,
            name = name,
            description = description,
            imagePath = thumbnail.path,
            imageExtension = thumbnail.extension
        )
    }

    //CharacterEntity → CharacterUI
    fun SuperheroEntity.toSuperhero(): Superhero {
        val image = if (imagePath == "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available") {
            "https://foni.papik.pro/uploads/posts/2024-09/foni-papik-pro-d1ls-p-kartinki-am-nyam-na-prozrachnom-fone-20.png"
        } else {
            "$imagePath.$imageExtension"
        }

        return Superhero(
            heroId = id,
            name = name,
            description = description,
            image = image
        )
    }

    //CharacterDTO → CharacterUI
    fun MarvelCharacter.toSuperhero(): Superhero {
        val image = if (thumbnail.path == "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available") {
            "https://foni.papik.pro/uploads/posts/2024-09/foni-papik-pro-d1ls-p-kartinki-am-nyam-na-prozrachnom-fone-20.png"
        } else {
            "${thumbnail.path}.${thumbnail.extension}"
        }

        return Superhero(
            heroId = id,
            name = name,
            description = description,
            image = image
        )
    }

    //CharacterUI → CharacterEntity
    fun Superhero.toEntity(): SuperheroEntity {
        val (path, extension) = if (image == "https://foni.papik.pro/uploads/posts/2024-09/foni-papik-pro-d1ls-p-kartinki-am-nyam-na-prozrachnom-fone-20.png") {
            "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available" to "jpg"
        } else {
            val lastDotIndex = image.lastIndexOf(".")
            if (lastDotIndex == -1) {
                image to "jpg"
            } else {
                image.substring(0, lastDotIndex) to image.substring(lastDotIndex + 1)
            }
        }

        return SuperheroEntity(
            id = heroId,
            name = name,
            description = description,
            imagePath = path,
            imageExtension = extension
        )
    }
}