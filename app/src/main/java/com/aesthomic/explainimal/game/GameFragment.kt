package com.aesthomic.explainimal.game


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.aesthomic.explainimal.R
import com.aesthomic.explainimal.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_game, container, false)

        viewModel = ViewModelProviders.of(this)
            .get(GameViewModel::class.java)

        binding.btnGameCorrect.setOnClickListener { onCorrect() }
        binding.btnGameSkip.setOnClickListener { onSkip() }
        binding.btnGameFinish.setOnClickListener { onFinish() }
        updateWordText()
        updateScoreText()

        return binding.root
    }

    private fun onSkip() {
        viewModel.onSkip()
        updateWordText()
        updateScoreText()
    }

    private fun onCorrect() {
        viewModel.onCorrect()
        updateWordText()
        updateScoreText()
    }

    private fun onFinish() {
        val action = GameFragmentDirections
            .actionGameDestinationToScoreDestination(viewModel.score.value ?: 0)
        findNavController().navigate(action)
    }

    private fun updateWordText() {
        binding.tvGameAnimal.text = viewModel.word.value
    }

    private fun updateScoreText() {
        binding.tvGameScore.text = viewModel.score.value.toString()
    }

}
