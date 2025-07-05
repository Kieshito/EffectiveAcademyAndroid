package com.example.effectiveacademy.ui.screen.heroes

sealed class SuperheroListEvent {
    data object GetSuperheroes: SuperheroListEvent()
    data class OnSuperheroCardClick(val id: Int): SuperheroListEvent()
    data object LoadMore: SuperheroListEvent()
    data class UpdateDominantColor(val index: Int): SuperheroListEvent()
}
