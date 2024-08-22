package com.example.diceapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.diceapp.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        displayResult()

        return binding.root
    }

    private fun displayResult() {
        val playerScore = viewModel.playerScore.value ?: 0
        val botScore = viewModel.botScore.value ?: 0

        val resultText = if (playerScore > botScore) {
            "Victory!"
        } else {
            "Defeat!"
        }

        binding.resultText.text = resultText
        Toast.makeText(context, resultText, Toast.LENGTH_SHORT).show()
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_coinTossFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
