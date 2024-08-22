package com.example.diceapp.di

class GameRepository(private val scoreDao: GameDao) {
    suspend fun insert(score: GameResult) {
        scoreDao.insert(score)
    }

    suspend fun getScoreById(id: Int): GameResult? {
        return scoreDao.getScoreById(id)
    }
}