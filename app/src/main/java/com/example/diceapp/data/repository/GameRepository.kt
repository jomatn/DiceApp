package com.example.diceapp.data.repository

import com.example.diceapp.data.dao.GameDao
import com.example.diceapp.data.model.GameModel

class GameRepository(private val scoreDao: GameDao) {
    suspend fun insert(score: GameModel) {
        scoreDao.insert(score)
    }

    suspend fun getScoreById(id: Int): GameModel? {
        return scoreDao.getScoreById(id)
    }
}