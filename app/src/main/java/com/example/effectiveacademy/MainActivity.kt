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

/*TODO: 1)Вынести из первого экрана и на втором экране переиспользовать CenterCircleLoading()
*       2)Избавиться от оверкила с размерами экрана из первого ревью
*       3)Переделать механизм обработки стейта по примеру в песочнице
*       4)Подправить навигацию
*       5)Сделать более безопасное хранение ключей
*       6)Добавить модель и репозиторий в дата пакет
*       7)Подправить верстку с modifier
*       8)Сделать второй экран более презентабельным по виду, вытянутые картинки на современном
*  устройстве с вытянутым экраном - ужас
*       9)Переделать нормально названии и назвать адекватно, используя DTO*/
