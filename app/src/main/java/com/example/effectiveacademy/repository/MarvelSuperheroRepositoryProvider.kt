package com.example.effectiveacademy.repository

object MarvelSuperheroRepositoryProvider {

    private val repository: ISuperheroRepository by lazy {
        MarvelSuperheroRepository()
    }

    fun provideRepository(): ISuperheroRepository {
        return repository
    }

}