package com.example.effectiveacademy.ui.screen.heroes

import androidx.annotation.OptIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.util.UnstableApi
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.example.effectiveacademy.R
import com.example.effectiveacademy.model.Superhero
import com.example.effectiveacademy.repository.MarvelSuperheroRepositoryProvider
import com.example.effectiveacademy.ui.navigation.NavigationComponent
import com.example.effectiveacademy.ui.screen.CenterCircleLoading


@Composable
fun SuperheroListScreen(
    navigationComponent: NavigationComponent,
    viewModel: SuperheroListViewModel = viewModel(
        factory = SuperheroListViewModelFactory(
            MarvelSuperheroRepositoryProvider.provideRepository(),
            navigationComponent
        )
    )
) {
    val state = viewModel.state.collectAsState().value
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2A2A2A))
    ) {
        if (state.error?.isNotEmpty() == true){
            Text(stringResource(R.string.error))
        } else if (state.isLoading){
           CenterCircleLoading()
        } else {
            Spacer(modifier = Modifier.height(screenHeight * 0.05f))
            Logo(screenWidth)
            Spacer(modifier = Modifier.height(screenHeight * 0.02f))
            Title(screenWidth)
            Spacer(modifier = Modifier.height(screenHeight * 0.05f))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = screenHeight * 0.05f)
            ) {
                if (state.superheroes?.isNotEmpty() == true) {
                    HeroList(
                        heroes = state.superheroes,
                        onEvent = viewModel::onEvent,
                        screenWidth = screenWidth,
                        screenHeight = screenHeight
                    )
                    Spacer(modifier = Modifier.height(screenHeight * 0.02f))
                } else {
                    Text(stringResource(R.string.empty_list))
                }
            }
        }
    }
}



@OptIn(UnstableApi::class)
@Composable
fun Logo(screenWidth: androidx.compose.ui.unit.Dp) {
    val painter = rememberAsyncImagePainter(
        model = "https://iili.io/JMnuvbp.png",
        onError = {}
    )
    val state by painter.state.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(screenWidth * 0.12f),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            is AsyncImagePainter.State.Empty,
            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator()
            }
            is AsyncImagePainter.State.Success -> {
                Image(
                    painter = painter,
                    contentDescription = stringResource(R.string.marvel_logo),
                    modifier = Modifier
                        .width(screenWidth * 0.45f)
                        .height(screenWidth * 0.12f)
                )
            }
            is AsyncImagePainter.State.Error -> {}
        }
    }
}

@Composable
fun Title(screenWidth: androidx.compose.ui.unit.Dp) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(screenWidth * 0.1f),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = stringResource(R.string.choose_hero_text),
            color = Color.White,
            modifier = Modifier,
            fontSize = (screenWidth * 0.075f).value.sp,
            fontWeight = FontWeight.W800
        )
    }
}

@Composable
fun HeroList(
    heroes: List<Superhero>?,
    onEvent: (SuperheroListEvent) -> Unit,
    screenWidth: androidx.compose.ui.unit.Dp,
    screenHeight: androidx.compose.ui.unit.Dp
) {
    val listState = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(listState)

    LazyRow(
        state = listState,
        flingBehavior = snapBehavior,
        horizontalArrangement = Arrangement.spacedBy(screenWidth * 0.04f),
        contentPadding = PaddingValues(horizontal = screenWidth * 0.04f),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(heroes ?: emptyList()) { hero ->
            HeroCard(
                hero = hero,
                onEvent = onEvent,
                screenWidth = screenWidth,
                screenHeight = screenHeight
            )
        }
    }
}

@Composable
fun HeroCard(
    hero: Superhero,
    onEvent: (SuperheroListEvent) -> Unit,
    screenWidth: androidx.compose.ui.unit.Dp,
    screenHeight: androidx.compose.ui.unit.Dp
) {
    Card(
        modifier = Modifier
            .size(
                width = screenWidth * 0.85f,
                height = screenHeight * 0.75f
            )
            .clickable {
                onEvent(SuperheroListEvent.OnSuperheroCardClick(hero.heroId))
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = hero.image,
                contentDescription = hero.name,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.25f))
            )
            Text(
                text = hero.name,
                color = Color.White,
                fontSize = (screenWidth * 0.07f).value.sp,
                fontWeight = FontWeight.W800,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = screenHeight * 0.04f)
                    .padding(horizontal = screenWidth * 0.05f),
            )
        }
    }
}
