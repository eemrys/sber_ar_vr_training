package com.example.exercise9.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exercise9.domain.Trailer

@Dao
interface TrailerDao {

    @Query("SELECT * FROM Trailer WHERE movieId = :movieId")
    fun getTrailerById(movieId: String): LiveData<Trailer?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(video: Trailer)
}