package com.example.exercise10.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exercise10.domain.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie ORDER BY popularity DESC")
    fun getAll(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg movies: Movie)
}