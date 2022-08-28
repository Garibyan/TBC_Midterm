package com.garibyan.armen.tbc_midterm.utils.extentions

import android.graphics.Color
import kotlin.random.Random

fun Int.Companion.randomColor(): Int {
    return Color.argb(180,
        Random.nextInt(256),
        Random.nextInt(256),
        Random.nextInt(256))
}