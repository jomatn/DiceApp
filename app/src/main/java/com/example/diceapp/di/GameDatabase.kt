package com.example.diceapp.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
// AppDatabase.kt
@Database(entities = [GameResult::class], version = 1, exportSchema = false)
abstract class GameDatabase : RoomDatabase() {
    abstract fun scoreDao(): GameDao

    companion object {
        @Volatile
        private var INSTANCE: GameDatabase? = null

        fun getDatabase(context: Context): GameDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameDatabase::class.java,
                    "score_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
