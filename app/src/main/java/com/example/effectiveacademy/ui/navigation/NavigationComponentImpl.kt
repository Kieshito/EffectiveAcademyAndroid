package com.example.effectiveacademy.ui.navigation

import androidx.navigation.NavController
import com.example.effectiveacademy.ui.screen.MarvelAppScreen

class NavigationComponentImpl(
    private val navController: NavController
) : NavigationComponent {
    override fun navigateBack() {
        navController.popBackStack()
    }

    override fun navigateToHeroInfo(heroId: Int) {
        navController.navigate("${MarvelAppScreen.SuperheroInfo.name}/$heroId")
    }
} 