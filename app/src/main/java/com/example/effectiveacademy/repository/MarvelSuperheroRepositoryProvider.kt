package com.example.effectiveacademy.repository

import android.content.Context
import com.example.effectiveacademy.repository.interfaces.ISuperheroRepository

object MarvelSuperheroRepositoryProvider {
    private var repository: ISuperheroRepository? = null

    fun provideRepository(context: Context): ISuperheroRepository {
        return repository ?: MarvelRepositoryImpl(
            networkRepository = NetworkRepositoryImpl(),
            localRepository = LocalRepositoryImpl(context)
        ).also { repository = it }
    }
}