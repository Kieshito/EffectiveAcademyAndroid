package com.example.effectiveacademy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SuperheroEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun superheroDao(): SuperheroDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "marvel_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}