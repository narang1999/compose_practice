package com.example.composebasics.homescreen.data.repository

import com.example.composebasics.data.MovieResponse
import com.example.composebasics.data.NetworkResult
import com.example.composebasics.homescreen.data.BaseMovieData
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
   suspend fun getMovieData(): State<MovieResponse>
}

sealed class State<out T>{
    data class Success<out T>(val data : T): State<T>()
    data class Error(var throwable: Throwable?):State<Nothing>()
}