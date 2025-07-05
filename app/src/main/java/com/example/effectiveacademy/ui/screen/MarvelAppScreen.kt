package com.example.effectiveacademy.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.effectiveacademy.ui.screen.heroes.SuperheroListScreen
import com.example.effectiveacademy.ui.navigation.NavigationComponentImpl
import com.example.effectiveacademy.ui.screen.heroInfo.SuperheroInfoScreen

enum class MarvelAppScreen {
    SuperheroesList,
    SuperheroInfo
}

@Composable
fun MarvelApp() {
    val navController = rememberNavController()
    val navigationComponent = NavigationComponentImpl(navController)

    NavHost(
        navController = navController,
        startDestination = MarvelAppScreen.SuperheroesList.name
    ) {
        composable(MarvelAppScreen.SuperheroesList.name) {
            SuperheroListScreen(navigationComponent)
        }
        composable("${MarvelAppScreen.SuperheroInfo.name}/{heroId}") { backStackEntry ->
            val heroId = backStackEntry.arguments?.getString("heroId")?.toInt()
            if (heroId != null) {
                SuperheroInfoScreen(heroId, navigationComponent)
            }
        }
    }
}
