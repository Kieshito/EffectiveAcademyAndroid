package com.example.effectiveacademy.ui.screen.heroes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.effectiveacademy.repository.interfaces.ISuperheroRepository
import com.example.effectiveacademy.ui.navigation.NavigationComponent

class SuperheroListViewModelFactory(
    private val navigationComponent: NavigationComponent,
    private val repository: ISuperheroRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SuperheroListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SuperheroListViewModel(
                repository,
                navigationComponent
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
} 