package com.example.effectiveacademy.ui.screen.heroInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectiveacademy.repository.ISuperheroRepository
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
        loadHero()
    }

    private fun loadHero() {
        viewModelScope.launch {
            try {
                val hero = repositoryProvider.findSuperHerobyId(heroId)
                _state.value = _state.value.copy(
                    isLoading = false,
                    hero = hero
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun onEvent(event: SuperheroInfoEvent) {
        when (event) {
            is SuperheroInfoEvent.OnBackClick -> {
                navigationComponent.navigateBack()
            }
            is SuperheroInfoEvent.OnSuperheroLoaded -> {
                _state.value = _state.value.copy(
                    isLoading = false,
                    hero = event.hero
                )
            }
            is SuperheroInfoEvent.OnError -> {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = event.message
                )
            }
        }
    }
} 