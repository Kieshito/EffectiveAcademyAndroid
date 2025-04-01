package com.example.effectiveacademy.ui.screen.heroInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.effectiveacademy.repository.ISuperheroRepository
import com.example.effectiveacademy.ui.navigation.NavigationComponent

class SuperheroInfoViewModelFactory(
    private val repository: ISuperheroRepository,
    private val navigationComponent: NavigationComponent,
    private val id: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SuperheroInfoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SuperheroInfoViewModel(id, navigationComponent, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}