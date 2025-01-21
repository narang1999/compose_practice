package com.example.composebasics.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composebasics.data.MovieApi
import com.example.composebasics.data.MovieResponse
import com.example.composebasics.homescreen.data.BaseMovieData
import com.example.composebasics.homescreen.data.MovieViewType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(var movieApi: MovieApi?):ViewModel() {
    private val _nowShowingMovies: MutableStateFlow<List<BaseMovieData>?> = MutableStateFlow(null)

    val nowShowingMovies: MutableStateFlow<List<BaseMovieData>?> = _nowShowingMovies

    fun getMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            _nowShowingMovies.emit( listOf(BaseMovieData(movie = movieApi?.getMovieList()?.data?.results, header = "Now showing", type = MovieViewType.Horizontal), BaseMovieData(movie = movieApi?.getMovieList()?.data?.results, header = "Popular", type = MovieViewType.Vertical)))
        }
    }
}