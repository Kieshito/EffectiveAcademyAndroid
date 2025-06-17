package com.example.effectiveacademy.ui.screen.heroes

import com.example.effectiveacademy.model.Superhero
import androidx.compose.ui.graphics.Color
import com.example.effectiveacademy.ui.theme.MarvelDarkBackground
import com.example.effectiveacademy.ui.theme.MarvelLightBackground

data class SuperheroListState (
    val isLoading: Boolean,
    val isLoadingMore: Boolean,
    val superheroes : List<Superhero>?,
    val error: String?,
    val dominantColor: Color = MarvelDarkBackground
)