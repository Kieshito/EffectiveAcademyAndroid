package com.example.effectiveacademy.ui.screen.heroInfo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectiveacademy.repository.interfaces.ISuperheroRepository
import com.example.effectiveacademy.ui.navigation.NavigationComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SuperheroInfoViewModel(
    private val heroId: Int,
    private val navigationComponent: NavigationComponent,
    private val repositoryProvider: ISuperheroRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SuperheroInfoState(isLoading = true))
    val state: StateFlow<SuperheroInfoState> = _state.asStateFlow()

    init {
        Log.d("SuperheroInfoViewModel", "Initializing with heroId: $heroId")
        loadHero()
    }

    private fun loadHero() {
        viewModelScope.launch {
            Log.d("SuperheroInfoViewModel", "Starting to load hero data")
            repositoryProvider.findSuperHerobyId(heroId)
                .onSuccess { character ->
                    Log.d("SuperheroInfoViewModel", "Hero loaded successfully: ${character.name}, image: ${character.image}")
                    _state.value = _state.value.copy(
                        isLoading = false,
                        hero = character
                    )
                }
                .onFailure { error ->
                    Log.e("SuperheroInfoViewModel", "Error loading hero: ${error.message}", error)
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
        }
    }

    fun onEvent(event: SuperheroInfoEvent) {
        when (event) {
            is SuperheroInfoEvent.OnBackClick -> {
                Log.d("SuperheroInfoViewModel", "Back button clicked")
                navigationComponent.navigateBack()
            }
            is SuperheroInfoEvent.OnSuperheroLoaded -> {
                Log.d("SuperheroInfoViewModel", "Hero loaded via event: ${event.hero.name}")
                _state.value = _state.value.copy(
                    isLoading = false,
                    hero = event.hero
                )
            }
            is SuperheroInfoEvent.OnError -> {
                Log.e("SuperheroInfoViewModel", "Error event received: ${event.message}")
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = event.message
                )
            }
        }
    }
} 