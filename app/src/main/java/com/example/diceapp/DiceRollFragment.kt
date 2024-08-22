package com.example.diceapp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.diceapp.databinding.FragmentDiceRollBinding

class DiceRollFragment : Fragment() {

    private lateinit var binding: FragmentDiceRollBinding
    private lateinit var viewModel: GameViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dice_roll, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        navController = findNavController()

        binding.highButton.setOnClickListener { rollDice(3) }
        binding.mediumButton.setOnClickListener { rollDice(2) }
        binding.lowButton.setOnClickListener { rollDice(1) }

        return binding.root
    }

    private fun rollDice(multiplier: Int) {
        val playerRoll = (1..6).random() * multiplier
        val botRoll = (1..6).random() * multiplier

        viewModel.updateScores(playerRoll, botRoll)
        binding.diceAnimation.playAnimation()

        binding.diceAnimation.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                if (viewModel.round.value == 3) {
                    navController.navigate(R.id.action_diceRollFragment_to_resultFragment)
                } else {
                    viewModel.nextRound()
                }
            }
        })
    }
}
