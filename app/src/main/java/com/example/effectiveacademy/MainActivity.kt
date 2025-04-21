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

/*TODO: 2)!!!Избавиться от оверкила с размерами экрана из первого ревью (нужна консультация)
*       3)!!Переделать механизм обработки стейта по примеру в песочнице
*       4)!Подправить навигацию
*       5)!!!Сделать (cпросить) безопасное хранение ключей
*       6)!!Добавить модель и репозиторий в дата пакет
*       8)!!!Добавить треугольник позади листа персонажа, а так же сделать изменение размера листа
*       9)!Переделать нормально названия и назвать адекватно, используя DTO*/
