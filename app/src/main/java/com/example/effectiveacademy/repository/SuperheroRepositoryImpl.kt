package com.example.effectiveacademy.repository

import com.example.effectiveacademy.model.Superhero

class SuperheroRepositoryImpl: ISuperheroRepository {
    private val superheroes: MutableList<Superhero> = mutableListOf()

    init {
        superheroes.addAll(
            listOf(
                Superhero(
                    heroId = 1,
                    name = "Deadpool",
                    description = "Please don't make the super suit green...or animated!",
                    image = "https://iili.io/JMnAfIV.png"
                ),
                Superhero(
                    heroId = 2,
                    name = "Iron Man",
                    description = "I AM IRON MAN",
                    image = "https://iili.io/JMnuDI2.png"
                ),
                Superhero(
                    heroId = 3,
                    name = "Spider Man",
                    description = "In iron suit",
                    image = "https://iili.io/JMnuyB9.png"
                )
            )
        )
    }

    override suspend fun getSuperHeroes(): List<Superhero> {
        return superheroes
    }

    override suspend fun findSuperHerobyId(id: Int): Superhero {
        return superheroes[id-1]
    }

}