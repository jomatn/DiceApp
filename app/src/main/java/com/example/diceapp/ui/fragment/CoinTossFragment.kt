package com.example.diceapp.ui.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.diceapp.ui.viewModel.GameViewModel
import com.example.diceapp.R
import com.example.diceapp.databinding.FragmentCoinTossBinding

class CoinTossFragment : Fragment() {

    private var _binding: FragmentCoinTossBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GameViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoinTossBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)
        navController = findNavController()

        binding.headsButton.setOnClickListener { flipCoin(true) }
        binding.tailsButton.setOnClickListener { flipCoin(false) }

    }

    private fun flipCoin(playerChoice: Boolean) {
        val result = (0..1).random() == 1
        binding.coinAnimation.playAnimation()

        binding.coinAnimation.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator, isReverse: Boolean) {
                super.onAnimationEnd(animation)
                if (result == playerChoice) {
                    Toast.makeText(context, "You start!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Bot starts!", Toast.LENGTH_SHORT).show()
                }
                navController.navigate(R.id.action_coinTossFragment_to_diceRollFragment)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
