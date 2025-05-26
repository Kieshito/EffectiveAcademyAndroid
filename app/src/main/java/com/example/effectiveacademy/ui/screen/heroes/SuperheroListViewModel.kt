package com.example.effectiveacademy.ui.screen.heroes

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectiveacademy.extractDominantColor
import com.example.effectiveacademy.loadBitmapFromUrl
import com.example.effectiveacademy.repository.interfaces.ISuperheroRepository
import com.example.effectiveacademy.ui.navigation.NavigationComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SuperheroListViewModel(
    private val repository: ISuperheroRepository,
    private val navigationComponent: NavigationComponent
) : ViewModel() {

    private val _state = MutableStateFlow(
        SuperheroListState(
            isLoading = false,
            isLoadingMore = false,
            superheroes = null,
            error = null,
            dominantColor = Color(0xFF2A2A2A)
        )
    )

    private var currentOffset = 0

    val state: StateFlow<SuperheroListState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getSuperheroList()
            while (_state.value.superheroes == null) {
                delay(10)
            }
            updateDominantColor(0)
        }
    }

    private fun getSuperheroList() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            repository.getSuperHeroes()
                .onSuccess { superheroes ->
                    _state.update { it.copy(
                        superheroes = superheroes,
                        isLoading = false)
                    }
                }
                .onFailure { error ->
                    _state.update { it.copy(
                        error = error.message,
                        isLoading = false)
                    }
                }
        }
    }

    private fun getIncreasedHeroList(){
        if (_state.value.isLoadingMore) return
        viewModelScope.launch {
            _state.update{ it.copy(isLoadingMore = true) }
            currentOffset += 5
            repository.getSuperHeroes(offset = currentOffset)
                .onSuccess { newSuperheroes ->
                    val currentList = _state.value.superheroes?.toMutableList() ?: mutableListOf()
                    currentList.addAll(newSuperheroes)
                    _state.update { it.copy(
                        superheroes = currentList,
                        isLoadingMore = false
                        )
                    }
                }
                .onFailure { error ->
                    _state.update { it.copy(
                        error = error.message,
                        isLoadingMore = false
                        )
                    }
                }
        }
    }

    private fun updateDominantColor(index: Int) {
        viewModelScope.launch {
            val hero = _state.value.superheroes?.getOrNull(index) ?: return@launch
            try {
                val bitmap = withContext(Dispatchers.IO) {
                    loadBitmapFromUrl(hero.image)
                }
                val color = extractDominantColor(bitmap)
                _state.update { it.copy(dominantColor = color) }
            } catch (e: Exception) {
                _state.update { it.copy(dominantColor = Color(0xFFED1D24)) }
            }
        }
    }

    fun onEvent(event: SuperheroListEvent) {
        when (event) {
            is SuperheroListEvent.GetSuperheroes -> getSuperheroList()
            is SuperheroListEvent.LoadMore -> getIncreasedHeroList()
            is SuperheroListEvent.OnSuperheroCardClick -> {
                navigationComponent.navigateToHeroInfo(event.id)
            }
            is SuperheroListEvent.UpdateDominantColor -> {
                updateDominantColor(event.index)
            }
        }
    }
}


