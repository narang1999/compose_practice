package com.example.composebasics.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composebasics.homescreen.data.BaseMovieData
import com.example.composebasics.homescreen.data.MovieViewType
import com.example.composebasics.homescreen.data.repository.IMovieRepository
import com.example.composebasics.homescreen.data.repository.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(var movieApi: IMovieRepository?) : ViewModel() {
    private val _nowShowingMovies: MutableStateFlow<State<List<BaseMovieData>>?> =
        MutableStateFlow(null)

    val nowShowingMovies: MutableStateFlow<State<List<BaseMovieData>>?> = _nowShowingMovies

    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            var response = movieApi?.getMovieData()
            when (response) {
                is State.Success -> {
                    _nowShowingMovies.emit(
                        State.Success(
                            listOf(
                                BaseMovieData(
                                    movie = response.data.results,
                                    header = "Now showing",
                                    type = MovieViewType.Horizontal
                                ), BaseMovieData(
                                    movie = response.data.results,
                                    header = "Popular",
                                    type = MovieViewType.Vertical
                                )
                            )
                        )
                    )
                }

                is State.Error -> {
                    _nowShowingMovies.emit(State.Error(response.throwable))
                }

                else -> {}
            }
        }
    }
}


