package com.example.effectiveacademy.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun CenterCircleLoading(){
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}