package com.aesthomic.explainimal.game

import androidx.lifecycle.ViewModel
import com.aesthomic.explainimal.data.Animal
import com.aesthomic.explainimal.data.AnimalData

class GameViewModel: ViewModel() {
    var word = ""
    var score = 0

    private lateinit var listAnimals: MutableList<Animal>

    init {
        resetList()
        nextWord()
    }

    private fun resetList() {
        listAnimals = mutableListOf()
        listAnimals.addAll(AnimalData.listAnimals)
        listAnimals.shuffle()
    }

    private fun nextWord() {
        if (listAnimals.isEmpty()) {
            resetList()
        }
        word = listAnimals.removeAt(0).name
    }

    fun onCorrect() {
        score++
        nextWord()
    }

    fun onSkip() {
        score -= 3
        nextWord()
    }
}