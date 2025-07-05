package com.example.effectiveacademy.ui.screen.heroInfo

import com.example.effectiveacademy.model.Superhero

sealed class SuperheroInfoEvent {
    data object OnBackClick : SuperheroInfoEvent()
    data class OnSuperheroLoaded(val hero: Superhero) : SuperheroInfoEvent()
    data class OnError(val message: String) : SuperheroInfoEvent()
} 