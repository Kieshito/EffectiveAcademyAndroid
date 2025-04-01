package com.example.effectiveacademy.ui.screen.heroes

import com.example.effectiveacademy.model.Superhero

data class SuperheroListState (
    val isLoading: Boolean,
    val superheroes : List<Superhero>?,
    val error: String?
)