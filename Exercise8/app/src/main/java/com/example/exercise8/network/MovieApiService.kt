package com.example.exercise8.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "7895bd2bbb4dbd1f990a316ebf4db876"

interface MovieApiService {
    @GET("movie/popular")
    fun getMovieListAsync(@Query("api_key" ) apiKey: String = API_KEY):
            Deferred<MovieList>
}