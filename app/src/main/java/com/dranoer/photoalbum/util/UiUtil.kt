package com.dranoer.photoalbum.util

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

fun getRandomColor(): Color {
    val grayShade = Random.nextInt(165, 256)
    return Color(grayShade, grayShade, grayShade)
}