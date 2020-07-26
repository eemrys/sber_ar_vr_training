package com.example.exercise9.network

import com.example.exercise9.domain.Movie
import com.example.exercise9.network.dto.MovieDto
import com.example.exercise9.network.dto.MovieList
import com.example.exercise9.network.dto.TrailerDto

class MovieMapper {
    companion object {
        private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"
        private const val BACKDROP_BASE_URL = "https://image.tmdb.org/t/p/w780"
        private const val YOUTUBE_BASE_URL = "https://www.youtube.com/watch?v="
    }

    fun mapToMovies(movieList: MovieList): Array<Movie> {
        return movieList.results.map { mapperUtil(it) }.toTypedArray()
    }

    private fun mapperUtil(movie: MovieDto): Movie {
        return Movie(
            id = movie.id,
            title = movie.title,
            poster = POSTER_BASE_URL + movie.poster,
            posterWide = BACKDROP_BASE_URL + movie.posterWide,
            summary = movie.summary,
            releaseDate = movie.releaseDate,
            popularity = movie.popularity
        )
    }

    fun mapTrailerUrl(trailerItem: TrailerDto): String {
        return YOUTUBE_BASE_URL + trailerItem.url
    }
}