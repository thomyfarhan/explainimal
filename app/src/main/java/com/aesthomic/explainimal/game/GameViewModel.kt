package com.aesthomic.explainimal.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aesthomic.explainimal.data.Animal
import com.aesthomic.explainimal.data.AnimalData

class GameViewModel: ViewModel() {
    var word = MutableLiveData<String>()
    var score = MutableLiveData<Int>()

    private lateinit var listAnimals: MutableList<Animal>

    init {
        resetList()
        nextWord()
        score.value = 0
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
        word.value = listAnimals.removeAt(0).name
    }

    fun onCorrect() {
        score.value = score.value?.plus(1)
        nextWord()
    }

    fun onSkip() {
        score.value = score.value?.minus(2)
        nextWord()
    }
}