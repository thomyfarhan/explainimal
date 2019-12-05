package com.aesthomic.explainimal.score


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.aesthomic.explainimal.R
import com.aesthomic.explainimal.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {

    private lateinit var binding: FragmentScoreBinding
    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_score, container, false)

        viewModelFactory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(arguments!!).score)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ScoreViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.eventPlayAgain.observe(this, Observer { playAgain ->
            if (playAgain) {
                findNavController().navigate(ScoreFragmentDirections
                    .actionScoreDestinationToGameDestination())
                viewModel.onPlayAgainComplete()
            }
        })

        // Navigate to Title Fragment when back button clicked
        // I added these lines to make animation transition in navigation works
        // popUpTo TitleFragment in GameFragment Action was deleted and exchanged by this
        requireActivity().onBackPressedDispatcher
            .addCallback(this, object: OnBackPressedCallback(true) {

                override fun handleOnBackPressed() {
                    findNavController().navigate(ScoreFragmentDirections
                        .actionScoreDestinationToTitleDestination())
                }
            })

        return binding.root
    }

}
