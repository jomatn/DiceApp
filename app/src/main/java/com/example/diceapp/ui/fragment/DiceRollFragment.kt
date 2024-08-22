package com.example.diceapp.ui.fragment

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
import com.example.diceapp.ui.viewModel.GameViewModel
import com.example.diceapp.R
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        navController = findNavController()

        binding.highButton.setOnClickListener { rollDice(3) }
        binding.mediumButton.setOnClickListener { rollDice(2) }
        binding.lowButton.setOnClickListener { rollDice(1) }
    }

    private fun rollDice(multiplier: Int) {
        val playerRoll = (1..6).random() * multiplier
        val botRoll = (1..6).random() * multiplier

        viewModel.updateScores(playerRoll, botRoll)

       val diceRollResult = playerRoll / multiplier

        val diceImageResource = when (diceRollResult) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_1
        }
        binding.diceImage.setImageResource(diceImageResource)

        binding.diceImage.animate().alpha(1f).setDuration(500).setListener(object : AnimatorListenerAdapter() {
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
