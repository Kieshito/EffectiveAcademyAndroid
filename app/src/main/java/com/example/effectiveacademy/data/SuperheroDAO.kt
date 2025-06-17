package com.example.effectiveacademy.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SuperheroDAO {
    @Query("SELECT * FROM superheroes")
    fun getAllSuperheroes(): Flow<List<SuperheroEntity>>

    @Query("SELECT * FROM superheroes WHERE id = :id")
    suspend fun getCharacterById(id: Int): SuperheroEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<SuperheroEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacter(character: SuperheroEntity)
}