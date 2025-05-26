package com.example.effectiveacademy.ui.screen.heroes.сomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.effectiveacademy.R

@Composable
fun Title(modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = stringResource(R.string.choose_hero_text),
            color = Color.White,
            modifier = Modifier,
            fontSize = 32.sp,
            fontWeight = FontWeight.W800
        )
    }
}