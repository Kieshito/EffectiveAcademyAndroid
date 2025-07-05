package com.example.effectiveacademy.ui.screen.heroes.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

@Composable
fun TriangleIndicator(
    modifier: Modifier,
    color: Color
) {
    Canvas(modifier = modifier) {
        val path = Path().apply {
            moveTo(size.width, size.height)
            lineTo(size.width, 0f)
            lineTo(0f, size.height)
            close()
        }
        drawPath(
            path = path,
            color = color
        )
    }
}

@Composable
fun HeroTriangleIndicator(
    modifier: Modifier,
    color: Color
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TriangleIndicator(
            modifier = modifier,
            color = color
        )
    }
}