package com.example.effectiveacademy.ui.screen.heroInfo

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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.effectiveacademy.R
import com.example.effectiveacademy.repository.MarvelSuperheroRepositoryProvider
import com.example.effectiveacademy.ui.navigation.NavigationComponent
import com.example.effectiveacademy.ui.screen.CenterCircleLoading

@Composable
fun SuperheroInfoScreen(
    heroId: Int,
    navigationComponent: NavigationComponent,
    viewModel: SuperheroInfoViewModel = viewModel(
        factory = SuperheroInfoViewModelFactory(
            heroId,
            navigationComponent,
            MarvelSuperheroRepositoryProvider.provideRepository(LocalContext.current)
        )
    )
) {
    val state by viewModel.state.collectAsState()
    val configuration = LocalConfiguration.current
    val layoutDirection = LocalLayoutDirection.current
    val isLandscape = configuration.screenWidthDp > configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            state.isLoading -> CenterCircleLoading()

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
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = if (isLandscape) screenHeight * 0.05f else screenWidth * 0.05f,
                            end = if (isLandscape) screenHeight * 0.05f else screenWidth * 0.05f,
                            top = if (isLandscape) screenHeight * 0.05f else screenHeight * 0.05f,
                            bottom = if (isLandscape) screenHeight * 0.05f else screenHeight * 0.05f
                        ),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = if (layoutDirection == androidx.compose.ui.unit.LayoutDirection.Rtl) 
                        Alignment.End else Alignment.Start
                ) {
                    Text(
                        text = hero.name,
                        fontSize = if (isLandscape) (screenHeight * 0.1f).value.sp else (screenWidth * 0.1f).value.sp,
                        color = Color.White,
                        fontWeight = FontWeight.W800,
                        textAlign = if (layoutDirection == androidx.compose.ui.unit.LayoutDirection.Rtl) 
                            TextAlign.End else TextAlign.Start
                    )
                    Spacer(modifier = Modifier.height(screenHeight * 0.02f))
                    Text(
                        text = hero.description,
                        fontSize = if (isLandscape) (screenHeight * 0.06f).value.sp else (screenWidth * 0.06f).value.sp,
                        color = Color.White,
                        textAlign = if (layoutDirection == androidx.compose.ui.unit.LayoutDirection.Rtl) 
                            TextAlign.End else TextAlign.Start,
                        fontWeight = FontWeight.W700
                    )
                }
                IconButton(
                    onClick = { viewModel.onEvent(SuperheroInfoEvent.OnBackClick) },
                    modifier = Modifier.padding(
                        start = if (isLandscape) screenHeight * 0.04f else screenWidth * 0.04f,
                        end = if (isLandscape) screenHeight * 0.04f else screenWidth * 0.04f,
                        top = if (isLandscape) screenHeight * 0.06f else screenHeight * 0.06f,
                        bottom = if (isLandscape) screenHeight * 0.06f else screenHeight * 0.06f
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back_button),
                        tint = Color.White,
                        modifier = Modifier.size(if (isLandscape) screenHeight * 0.08f else screenWidth * 0.08f)
                    )
                }
            }
        }
    }
}