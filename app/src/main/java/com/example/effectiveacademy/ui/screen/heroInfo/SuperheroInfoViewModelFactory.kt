package com.example.effectiveacademy.ui.screen.heroInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.effectiveacademy.repository.interfaces.ISuperheroRepository
import com.example.effectiveacademy.ui.navigation.NavigationComponent

class SuperheroInfoViewModelFactory(
    private val heroId: Int,
    private val navigationComponent: NavigationComponent,
    private val repository: ISuperheroRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SuperheroInfoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SuperheroInfoViewModel(
                heroId,
                navigationComponent,
                repository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}