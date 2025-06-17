package com.example.effectiveacademy

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL


suspend fun loadBitmapFromUrl(url: String): Bitmap {
    return withContext(Dispatchers.IO) {
        val inputStream = URL(url).openStream()
        BitmapFactory.decodeStream(inputStream)
    }
}

fun extractDominantColor(bitmap: Bitmap, isDarkTheme: Boolean = true): Color {
    val palette = Palette.from(bitmap).generate()

    return palette.vibrantSwatch?.rgb?.let { Color(it) } //яркий доминирующий
        ?: palette.mutedSwatch?.rgb?.let { Color(it) } // насыщенный домиенирующий
        ?: palette.dominantSwatch?.rgb?.let { Color(it) } //любой доминирующий
        ?: if (isDarkTheme) Color(0xFF2A2A2A) else Color(0xFFD0D0D0) //запасной вариант в зависимости от темы
}