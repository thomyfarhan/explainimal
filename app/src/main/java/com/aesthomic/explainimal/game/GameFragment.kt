package com.aesthomic.explainimal.game


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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

        binding.viewModel = viewModel

        viewModel.score.observe(this, Observer { newScore ->
            binding.tvGameScore.text = newScore.toString()
        })

        viewModel.word.observe(this, Observer { newWord ->
            binding.tvGameAnimal.text = newWord
        })

        viewModel.eventGameFinish.observe(this, Observer { hasFinished ->
            if (hasFinished) onFinish()
        })

        return binding.root
    }

    private fun onFinish() {
        val action = GameFragmentDirections
            .actionGameDestinationToScoreDestination(viewModel.score.value ?: 0)
        findNavController().navigate(action)
        viewModel.onGameFinishComplete()
    }

}
