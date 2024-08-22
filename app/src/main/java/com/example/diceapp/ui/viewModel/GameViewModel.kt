// GameViewModel.kt
package com.example.diceapp.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class GameViewModel(application: Application) : AndroidViewModel(application) {
    val playerScore = MutableLiveData<Int>(0)
    val botScore = MutableLiveData<Int>(0)
    val round = MutableLiveData<Int>(1)

    fun updateScores(player: Int, bot: Int) {
        playerScore.value = playerScore.value?.plus(player)
        botScore.value = botScore.value?.plus(bot)
    }

    fun nextRound() {
        round.value = round.value?.plus(1)
    }
}
