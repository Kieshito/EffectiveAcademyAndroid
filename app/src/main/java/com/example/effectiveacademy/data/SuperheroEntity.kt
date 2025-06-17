package com.example.effectiveacademy.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "superheroes")
data class SuperheroEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val imagePath: String,
    val imageExtension: String
)
