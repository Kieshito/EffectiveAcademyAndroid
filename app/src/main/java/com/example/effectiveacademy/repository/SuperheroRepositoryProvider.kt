package com.example.effectiveacademy.repository

class SuperheroRepositoryProvider {

    private val repository: ISuperheroRepository by lazy {
        SuperheroRepositoryImpl()
    }

    fun provideRepository(): ISuperheroRepository = repository
}