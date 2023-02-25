package com.example.quizapp.utils

import com.example.quizapp.R

/**
 * Created by Mukesh on 22-02-2023.
 */
object IconPicker {
    val icons = arrayOf(
        R.drawable.abc_icon,
        R.drawable.bible_icon,
        R.drawable.book_icon,
        R.drawable.dictionaryicon,
        R.drawable.documents_icon,
        R.drawable.education_icon,
        R.drawable.documents_icon,
        R.drawable.knowledge_icon,
        R.drawable.learn_icon,
        R.drawable.machine_icon,
        R.drawable.notebook_icon,
        R.drawable.reading_icon,
        R.drawable.readingicon,
        R.drawable.science_icon,
        R.drawable.study_icon,
        R.drawable.virus_icon,
        )
    var currentIconIndex = 0

    fun getIcon(): Int {
        currentIconIndex = (currentIconIndex + 1) % icons.size
        return icons[currentIconIndex]
    }
}