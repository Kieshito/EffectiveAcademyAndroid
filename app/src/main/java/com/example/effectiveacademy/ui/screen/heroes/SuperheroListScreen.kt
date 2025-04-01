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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.util.UnstableApi
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.example.effectiveacademy.model.Superhero
import com.example.effectiveacademy.repository.SuperheroRepositoryProvider
import com.example.effectiveacademy.ui.navigation.NavigationComponent

@Composable
fun SuperheroListScreen(
    navigationComponent: NavigationComponent,
    viewModel: SuperheroListViewModel = viewModel(
        factory = SuperheroListViewModelFactory(
            SuperheroRepositoryProvider().provideRepository(),
            navigationComponent
        )
    )
) {
    val state = viewModel.state.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2A2A2A))
    ) {
        if (state.error?.isNotEmpty() == true){
            Text("Error occurred")
        } else {
            Spacer(modifier = Modifier.height(45.dp))
            Logo()
            Spacer(modifier = Modifier.height(15.dp))
            Title()
            Spacer(modifier = Modifier.height(45.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 45.dp)
            ) {
                if (state.superheroes?.isNotEmpty() == true) {
                    HeroList(
                        heroes = state.superheroes,
                        onEvent = viewModel::onEvent
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                } else {
                    Text("Empty hero list")
                }
            }
        }
    }

}

@OptIn(UnstableApi::class)
@Composable
fun Logo() {
    val painter = rememberAsyncImagePainter(
        model = "https://iili.io/JMnuvbp.png",
        onError = {}
    )
    val state by painter.state.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
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
                    contentDescription = "Marvel Logo",
                    modifier = Modifier
                        .width(180.dp)
                        .height(50.dp)
                )
            }
            is AsyncImagePainter.State.Error -> {}
        }
    }
}

@Composable
fun Title() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = "Choose your hero",
            color = Color.White,
            modifier = Modifier,
            fontSize = (30.sp),
            fontWeight = (FontWeight.W800)
        )
    }
}

@Composable
fun HeroList(
    heroes: List<Superhero>?,
    onEvent: (SuperheroListEvent) -> Unit
) {
    val listState = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(listState)

    LazyRow(
        state = listState,
        flingBehavior = snapBehavior,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(heroes ?: emptyList()) { hero ->
            HeroCard(
                hero = hero,
                onEvent = onEvent
            )
        }
    }
}

@Composable
fun HeroCard(
    hero: Superhero,
    onEvent: (SuperheroListEvent) -> Unit
) {
    Card(
        modifier = Modifier
            .size(width = 350.dp, height = 650.dp)
            .clickable {
                onEvent(SuperheroListEvent.OnSuperheroCardClick(hero.heroId))
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
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
                fontSize = 28.sp,
                fontWeight = FontWeight.W800,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 30.dp)
                    .padding(horizontal = 20.dp),
            )
        }
    }
}