package com.example.exercise10.network

import com.example.exercise10.network.dto.MovieList
import com.example.exercise10.network.dto.TrailerList
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val API_KEY = "7895bd2bbb4dbd1f990a316ebf4db876"

interface MovieApiService {
    @GET("movie/popular")
    fun getMovieListAsync(
        @Query("api_key") apiKey: String = API_KEY):
            Deferred<MovieList>

    @GET("movie/{movieId}/videos")
    fun getMovieTrailerAsync(
        @Path("movieId") movieId: String,
        @Query("api_key") apiKey: String = API_KEY):
            Deferred<TrailerList>
}