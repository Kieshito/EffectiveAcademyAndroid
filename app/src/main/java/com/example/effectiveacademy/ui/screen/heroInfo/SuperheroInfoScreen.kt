package com.example.effectiveacademy.ui.screen.heroInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.effectiveacademy.repository.SuperheroRepositoryProvider
import com.example.effectiveacademy.ui.navigation.NavigationComponent
import com.example.effectiveacademy.ui.screen.heroes.SuperheroListViewModel
import com.example.effectiveacademy.ui.screen.heroes.SuperheroListViewModelFactory

@Composable
fun SuperheroInfoScreen(
    heroId: Int,
    navigationComponent: NavigationComponent,

    viewModel: SuperheroInfoViewModel = viewModel(
        factory = SuperheroInfoViewModelFactory(
            SuperheroRepositoryProvider().provideRepository(),
            navigationComponent,
            heroId
        )
    )
) {
    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        when {
            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.error != null -> {
                Text(
                    text = state.error!!,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.hero != null -> {
                val hero = state.hero!!
                AsyncImage(
                    model = hero.image,
                    contentDescription = hero.name,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxSize()
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 42.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = hero.name,
                        fontSize = 40.sp,
                        color = Color.White,
                        fontWeight = FontWeight.W800
                    )
                    Spacer(modifier = Modifier.size(15.dp))
                    Text(
                        text = hero.description,
                        fontSize = 25.sp,
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.W700
                    )
                }
                IconButton(
                    onClick = { viewModel.onEvent(SuperheroInfoEvent.OnBackClick) },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 45.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White

                    )
                }
            }
        }
    }
}