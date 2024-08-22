package com.example.diceapp.di

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score_table")
data class GameResult(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val playerScore: Int,
    val botScore: Int,
    val round: Int
)
