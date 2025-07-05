package com.example.effectiveacademy.ui.screen.heroInfo

import com.example.effectiveacademy.model.Superhero

data class SuperheroInfoState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val hero: Superhero? = null
) 