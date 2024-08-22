package com.example.diceapp.di

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(score: GameResult)

    @Query("SELECT * FROM score_table WHERE id = :id")
    suspend fun getScoreById(id: Int): GameResult?
}
