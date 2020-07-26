package com.example.exercise10.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exercise10.domain.Movie
import com.example.exercise10.domain.Trailer

@Database(entities = [Movie::class, Trailer::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract val movieDao: MovieDao
    abstract val trailerDao: TrailerDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java,
                        "videos")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}