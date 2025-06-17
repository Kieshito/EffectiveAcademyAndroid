package com.example.effectiveacademy.ui.screen.heroes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.effectiveacademy.repository.MarvelSuperheroRepositoryProvider
import com.example.effectiveacademy.ui.navigation.NavigationComponent
import com.example.effectiveacademy.ui.screen.CenterCircleLoading
import com.example.effectiveacademy.ui.screen.heroes.components.HeroList
import com.example.effectiveacademy.ui.screen.heroes.components.HeroTriangleIndicator
import com.example.effectiveacademy.ui.screen.heroes.components.Logo
import com.example.effectiveacademy.ui.screen.heroes.components.Title


@Composable
fun SuperheroListScreen(
    navigationComponent: NavigationComponent,
    viewModel: SuperheroListViewModel = viewModel(
        factory = SuperheroListViewModelFactory(
            navigationComponent,
            MarvelSuperheroRepositoryProvider.provideRepository(LocalContext.current)
        )
    )
) {
    val state = viewModel.state.collectAsState().value
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val listState = rememberLazyListState()

    //Долго думал какое решение придумать исходя из ревью, но обмыслить как сделать не получилось,
    //получилось у ИИ. Решение разобрал, но сомневаюсь в эфективности и элегатности, возможно есть
    //решение гораздо проще

    val currentHero = remember(listState) {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val visibleItemsInfo = layoutInfo.visibleItemsInfo

            if (visibleItemsInfo.isEmpty()) return@derivedStateOf null

            val viewportWidth = layoutInfo.viewportSize.width
            val viewportCenter = viewportWidth / 2
            val visibilityThreshold = viewportWidth * 0.2f

            visibleItemsInfo.minByOrNull { item ->
                val itemCenter = item.offset + (item.size / 2)
                kotlin.math.abs(itemCenter - viewportCenter)
            }?.let { item ->
                val itemVisibility = item.size - kotlin.math.abs(item.offset)
                if (itemVisibility > visibilityThreshold) {
                    item.index
                } else {
                    null
                }
            }
        }
    }

    LaunchedEffect(currentHero.value) {
        currentHero.value?.let { index ->
            viewModel.onEvent(SuperheroListEvent.UpdateDominantColor(index))
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.collect { lastVisibleItemIndex ->
            if (lastVisibleItemIndex != null) {
                val totalItems = listState.layoutInfo.totalItemsCount
                if (lastVisibleItemIndex >= totalItems - 1 && !state.isLoadingMore) {
                    viewModel.onEvent(SuperheroListEvent.LoadMore)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2A2A2A))
    ) {
        HeroTriangleIndicator(
            modifier = Modifier
                .size(screenWidth * 0.65f)
                .align(Alignment.BottomEnd),
            color = state.dominantColor
        )

        if (state.error?.isNotEmpty() == true){
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else if (state.isLoading){
           CenterCircleLoading()
        } else {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(screenHeight * 0.05f))
                Logo(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenWidth * 0.12f),
                    modifierImage = Modifier
                        .width(screenWidth * 0.45f)
                        .height(screenWidth * 0.12f)
                )
                Spacer(modifier = Modifier.height(screenHeight * 0.02f))
                Title(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenWidth * 0.1f)
                )
                Spacer(modifier = Modifier.height(screenHeight * 0.01f))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = screenHeight * 0.03f)
                ) {
                    if (state.superheroes?.isNotEmpty() == true) {
                        HeroList(
                            heroes = state.superheroes,
                            onEvent = viewModel::onEvent,
                            modifierList = Modifier
                                .fillMaxWidth()
                                .height(screenHeight * 0.7f),
                            modifierCard = Modifier
                                .size(
                                width = screenWidth * 0.85f,
                                height = screenHeight * 0.75f
                            ),
                            listState = listState
                        )
                        if (state.isLoadingMore) {
                            CenterCircleLoading()
                        }
                        Spacer(modifier = Modifier.height(screenHeight * 0.01f))
                    }
                }
            }
        }
    }
}







