package com.example.effectiveacademy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.effectiveacademy.ui.screen.MarvelApp
import com.example.effectiveacademy.ui.theme.EffectiveAcademyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            EffectiveAcademyTheme {
                MarvelApp()
            }
        }
    }
}
