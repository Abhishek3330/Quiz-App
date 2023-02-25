package com.example.quizapp.utils

/**
 * Created by Mukesh on 22-02-2023.
 */
object ColorPicker {
    val colors = arrayOf(
        "#03b1fc",
        "#a5fc03",
        "#dffc03",
        "#fcb603",
        "#fc5203",
        "#7a2904",
        "#5d047a",
        "#ad0369",
        "#fc4753",
        "#fc762d"
    )
    var currentColorIndex = 0

    fun getColor(): String {
        currentColorIndex = (currentColorIndex + 1) % colors.size
        return colors[currentColorIndex]
    }
}