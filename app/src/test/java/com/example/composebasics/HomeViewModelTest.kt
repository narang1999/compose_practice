package com.example.composebasics

import com.example.composebasics.data.IMovieApi
import com.example.composebasics.data.Movie
import com.example.composebasics.data.MovieApi
import com.example.composebasics.data.MovieResponse
import com.example.composebasics.data.NetworkResult
import com.example.composebasics.data.Success
import com.example.composebasics.homescreen.HomeViewModel
import com.example.composebasics.homescreen.data.BaseMovieData
import com.example.composebasics.homescreen.data.MovieViewType
import com.example.composebasics.homescreen.data.repository.IMovieRepository
import com.example.composebasics.homescreen.data.repository.MovieRepository
import com.example.composebasics.homescreen.data.repository.State
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class HomeViewModelTest {
    var homeViewModel: HomeViewModel? = null

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    lateinit var movieApi: IMovieRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun runBefore() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this) // Initialize mocks
        movieApi = mock(IMovieRepository::class.java)
        homeViewModel = HomeViewModel(movieApi)
    }

    @Test
    fun `check if getting correct data on success response`() {
        runBlocking {
            `when`(movieApi.getMovieData()).thenReturn(State.Success(movies))
            homeViewModel?.getMovies()

            val value = State.Success(listOf(
                BaseMovieData(
                    movie = movies.results,
                    header = "Now showing",
                    type = MovieViewType.Horizontal
                ),
                BaseMovieData(
                    movie = movies.results,
                    header = "Popular",
                    type = MovieViewType.Vertical
                )
            ))

            assertEquals(homeViewModel?.nowShowingMovies?.first(), value)
        }
    }

    @Test
    fun `check if getting error state on Error response`() {
        runBlocking {
           var error = State.Error(Throwable(message = "Not Found"))
            `when`(movieApi.getMovieData()).thenReturn(error)

            homeViewModel?.getMovies()

            assertEquals(homeViewModel?.nowShowingMovies?.first(), error)
        }
    }

    var movies: MovieResponse = MovieResponse(
        page = 1,
        results = listOf(
            Movie(
                adult = false,
                backdropPath = "/random_backdrop.jpg",
                genreIds = listOf(28, 12, 16),
                id = 12345,
                originalLanguage = "en",
                originalTitle = "Sample Movie",
                overview = "This is a sample movie overview with random details.",
                popularity = 87.5,
                posterPath = "/random_poster.jpg",
                releaseDate = "2023-12-01",
                title = "Sample Movie",
                video = false,
                voteAverage = 8.2,
                voteCount = 1500
            ),
            Movie(
                adult = false,
                backdropPath = "/another_backdrop.jpg",
                genreIds = listOf(35, 18),
                id = 67890,
                originalLanguage = "fr",
                originalTitle = "Un Film Exemple",
                overview = "A French sample movie overview for testing.",
                popularity = 72.3,
                posterPath = "/another_poster.jpg",
                releaseDate = "2024-01-15",
                title = "Example Movie",
                video = false,
                voteAverage = 7.8,
                voteCount = 980
            )
        ),
        totalPages = 10,
        totalResults = 200
    )

}