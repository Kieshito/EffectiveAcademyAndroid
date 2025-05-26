package com.example.effectiveacademy.ui.screen.heroes.сomponents

import androidx.annotation.OptIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.media3.common.util.UnstableApi
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.example.effectiveacademy.R

@OptIn(UnstableApi::class)
@Composable
fun Logo(modifier: Modifier, modifierImage: Modifier) {
    val painter = rememberAsyncImagePainter(
        model = "https://iili.io/JMnuvbp.png",
        onError = {}
    )
    val state by painter.state.collectAsState()
    Box(
        modifier = modifier
        ,
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
                    modifier = modifierImage
                )
            }
            is AsyncImagePainter.State.Error -> {}
        }
    }
}