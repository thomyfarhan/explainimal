package com.aesthomic.explainimal.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aesthomic.explainimal.data.Animal
import com.aesthomic.explainimal.data.AnimalData

class GameViewModel: ViewModel() {
    private lateinit var listAnimals: MutableList<Animal>

    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    init {
        resetList()
        nextWord()
        _score.value = 0
    }

    private fun resetList() {
        listAnimals = mutableListOf()
        listAnimals.addAll(AnimalData.listAnimals)
        listAnimals.shuffle()
    }

    private fun nextWord() {
        if (listAnimals.isEmpty()) {
            onGameFinish()
        } else {
            _word.value = listAnimals.removeAt(0).name
        }
    }

    fun onCorrect() {
        _score.value = score.value?.plus(1)
        nextWord()
    }

    fun onSkip() {
        _score.value = score.value?.minus(2)
        nextWord()
    }

    fun onGameFinish() {
        _eventGameFinish.value = true
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }
}