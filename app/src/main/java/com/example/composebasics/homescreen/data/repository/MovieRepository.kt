package com.example.composebasics.homescreen.data.repository

import com.example.composebasics.data.ApiError
import com.example.composebasics.data.Error
import com.example.composebasics.data.IMovieApi
import com.example.composebasics.data.MovieApi
import com.example.composebasics.data.MovieResponse
import com.example.composebasics.data.NetworkResult
import com.example.composebasics.data.Success

  class MovieRepository(private val movieApi: IMovieApi):IMovieRepository {
    override suspend fun getMovieData(): State<MovieResponse> {
        return when(val response = movieApi.getMovieList()){
            is Success -> {
                State.Success(data = response.data)
            }

            is Error  -> {
                State.Error(response.exception)
            }
        }
    }
}