package com.example.effectiveacademy.repository

import com.example.effectiveacademy.model.Superhero
import com.example.effectiveacademy.repository.interfaces.ILocalRepository
import com.example.effectiveacademy.repository.interfaces.INetworkRepository
import com.example.effectiveacademy.repository.interfaces.ISuperheroRepository
import kotlinx.coroutines.flow.Flow

class MarvelRepositoryImpl(
    private val networkRepository: INetworkRepository,
    private val localRepository: ILocalRepository
) : ISuperheroRepository {

    override suspend fun getSuperHeroes(offset: Int): Result<List<Superhero>> {
        return networkRepository.getCharacters(offset).also { result ->
            result.getOrNull()?.let { superheroes ->
                localRepository.insertSuperheroes(superheroes)
            }
        }
    }

    override suspend fun findSuperHerobyId(id: Int): Result<Superhero> {
        // Сначала проверяем локальную БД
        localRepository.getSuperheroById(id)?.let {
            return Result.success(it)
        }

        // Если нет в БД, запрашиваем из сети
        return networkRepository.getCharacterById(id).also { result ->
            result.getOrNull()?.let { superhero ->
                localRepository.insertSuperhero(superhero)
            }
        }
    }

    fun getAllSuperheroes(): Flow<List<Superhero>> {
        return localRepository.getAllSuperheroes()
    }
}