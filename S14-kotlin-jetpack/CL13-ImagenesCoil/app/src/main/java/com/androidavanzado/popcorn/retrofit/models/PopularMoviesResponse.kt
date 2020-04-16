package com.androidavanzado.popcorn.retrofit.models

import com.androidavanzado.popcorn.retrofit.models.Movie

data class PopularMoviesResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)