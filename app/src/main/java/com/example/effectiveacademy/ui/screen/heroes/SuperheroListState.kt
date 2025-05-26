package com.example.effectiveacademy.ui.screen.heroes

import com.example.effectiveacademy.model.Superhero
import androidx.compose.ui.graphics.Color

data class SuperheroListState (
    val isLoading: Boolean,
    val isLoadingMore: Boolean,
    val superheroes : List<Superhero>?,
    val error: String?,
    val dominantColor: Color = Color(0xFF2A2A2A)
)