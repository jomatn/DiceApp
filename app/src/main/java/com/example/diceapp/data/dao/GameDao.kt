package com.example.diceapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.diceapp.data.model.GameModel

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(score: GameModel)

    @Query("SELECT * FROM score_table WHERE id = :id")
    suspend fun getScoreById(id: Int): GameModel?

    @Delete
    suspend fun delete(score: GameModel)
}
