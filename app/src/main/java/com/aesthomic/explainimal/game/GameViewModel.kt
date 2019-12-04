package com.aesthomic.explainimal.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.aesthomic.explainimal.data.Animal
import com.aesthomic.explainimal.data.AnimalData

// Constant variables for time
private const val DONE = 0L
private const val ONE_SECOND = 1000L
private const val COUNTDOWN_TIME = 60000L
private const val COUNTDOWN_PANIC_TIME = 10000L

// Constant variables for buzz
private val CORRECT_BUZZ_PATTERN = longArrayOf(100, 100, 100, 100, 100, 100)
private val PANIC_BUZZ_PATTERN = longArrayOf(0, 200)
private val GAME_OVER_BUZZ_PATTERN = longArrayOf(0, 2000)
private val NO_BUZZ_PATTERN = longArrayOf(0)

class GameViewModel: ViewModel() {
    enum class BuzzType(val pattern: LongArray) {
        CORRECT(CORRECT_BUZZ_PATTERN),
        GAME_OVER(GAME_OVER_BUZZ_PATTERN),
        COUNTDOWN_PANIC(PANIC_BUZZ_PATTERN),
        NO_BUZZ(NO_BUZZ_PATTERN)
    }

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

    private val _eventBuzz = MutableLiveData<BuzzType>()
    val eventBuzz: LiveData<BuzzType>
        get() = _eventBuzz

    private val currentTime = MutableLiveData<Long>()

    val currentTimeString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    private val timer: CountDownTimer

    init {
        resetList()
        nextWord()
        _score.value = 0

        timer = object: CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onFinish() {
                currentTime.value = DONE
                _eventBuzz.value = BuzzType.GAME_OVER
                onGameFinish()
            }

            override fun onTick(millisUntilFinished: Long) {
                currentTime.value = millisUntilFinished/ONE_SECOND
                if (millisUntilFinished <= COUNTDOWN_PANIC_TIME) {
                    _eventBuzz.value = BuzzType.COUNTDOWN_PANIC
                }
            }

        }

        timer.start()
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
        _word.value = listAnimals.removeAt(0).name
    }

    fun onCorrect() {
        _score.value = score.value?.plus(1)
        _eventBuzz.value = BuzzType.CORRECT
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

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}
