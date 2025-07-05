package com.example.effectiveacademy.ui.screen.heroes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.effectiveacademy.model.Superhero
import com.example.effectiveacademy.ui.screen.heroes.SuperheroListEvent

@Composable
fun HeroList(
    heroes: List<Superhero>?,
    onEvent: (SuperheroListEvent) -> Unit,
    modifierList: Modifier,
    modifierCard: Modifier,
    listState: LazyListState
) {
    val snapBehavior = rememberSnapFlingBehavior(listState)

    LazyRow(
        state = listState,
        flingBehavior = snapBehavior,
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(24.dp),
        modifier = modifierList
    ) {
        items(heroes ?: emptyList()) { hero ->
            HeroCard(
                hero = hero,
                onEvent = onEvent,
                modifier = modifierCard
            )
        }
    }
}

@Composable
fun HeroCard(
    hero: Superhero,
    onEvent: (SuperheroListEvent) -> Unit,
    modifier: Modifier
) {
    val layoutDirection = LocalLayoutDirection.current
    
    Card(
        modifier = modifier
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
                    .background(
                        if (MaterialTheme.colorScheme.background == Color(0xFFD0D0D0)) {
                            Color.Black.copy(alpha = 0.25f)
                        } else {
                            Color.Black.copy(alpha = 0.4f)
                        }
                    )
            )
            Text(
                text = hero.name,
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.W800,
                modifier = Modifier
                    .align(if (layoutDirection == androidx.compose.ui.unit.LayoutDirection.Rtl) 
                        Alignment.BottomEnd else Alignment.BottomStart)
                    .padding(
                        bottom = 16.dp,
                        start = if (layoutDirection == androidx.compose.ui.unit.LayoutDirection.Rtl) 0.dp else 20.dp,
                        end = if (layoutDirection == androidx.compose.ui.unit.LayoutDirection.Rtl) 20.dp else 0.dp
                    )
            )
        }
    }
}