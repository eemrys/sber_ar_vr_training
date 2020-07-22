package com.example.exercise8.network

class MovieMapper {
    companion object {
        private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"
        private const val BACKDROP_BASE_URL = "https://image.tmdb.org/t/p/w780"
        private const val YOUTUBE_BASE_URL = "https://www.youtube.com/watch?v="
    }

    fun mapToMovies(movieList: MovieList): List<MovieItem> {
        return movieList.results.map { it -> mapperUtil(it) }
    }

    private fun mapperUtil(movie: MovieItem): MovieItem {
        return MovieItem(
            id = movie.id,
            title = movie.title,
            poster = POSTER_BASE_URL + movie.poster,
            posterWide = BACKDROP_BASE_URL + movie.posterWide,
            summary = movie.summary,
            releaseDate = movie.releaseDate)
    }
}