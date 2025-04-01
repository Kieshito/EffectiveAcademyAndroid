package com.example.effectiveacademy.ui.screen.heroes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectiveacademy.repository.ISuperheroRepository
import com.example.effectiveacademy.ui.navigation.NavigationComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SuperheroListViewModel(
    private val repository: ISuperheroRepository,
    private val navigationComponent: NavigationComponent
) : ViewModel() {

    private val _state = MutableStateFlow(
        SuperheroListState(false, null, null)
    )
    val state: StateFlow<SuperheroListState> = _state.asStateFlow()

    init {
        getSuperheroList()
    }

    private fun getSuperheroList() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val superheroes = repository.getSuperHeroes()
                _state.update { it.copy(superheroes = superheroes) }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            } finally {
                _state.update { it.copy(isLoading = false, error = null) }
            }
        }
    }

    fun onEvent(event: SuperheroListEvent) {
        when (event) {
            is SuperheroListEvent.GetSuperheroes -> getSuperheroList()
            is SuperheroListEvent.OnSuperheroCardClick -> {
                navigationComponent.navigateToHeroInfo(event.id)
            }
        }
    }
}


