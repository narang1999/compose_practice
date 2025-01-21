package com.example.composebasics.homescreen.data

import com.example.composebasics.data.Movie

data class BaseMovieData(val type :MovieViewType, val header:String, val movie: List<Movie>?)


enum class MovieViewType{
    Vertical,
    Horizontal
}