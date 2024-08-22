package com.example.diceapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score_table")
data class GameModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val playerScore: Int,
    val botScore: Int,
    val round: Int
)
