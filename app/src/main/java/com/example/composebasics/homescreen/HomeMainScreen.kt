package com.example.composebasics.homescreen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star

import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.composebasics.data.Movie
import com.example.composebasics.data.MovieResponse
import com.example.composebasics.homescreen.data.BaseMovieData
import com.example.composebasics.homescreen.data.MovieViewType
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeNewScreen(myViewModel: HomeViewModel?) {

    LaunchedEffect(Unit) {
        myViewModel?.getMovies()
    }
    val movieState: State<List<BaseMovieData>?>? =
        myViewModel?.nowShowingMovies?.collectAsState(null)
    LazyColumn {
        movieState?.value?.forEach {

            item { header(baseMovieData = it, { myViewModel.getMovies() }) }

            if (it.type == MovieViewType.Horizontal) {
                item {
                    LazyRow {
                        it.movie?.let {
                            items(it) {
                                movieItem(movie = it)
                            }
                        }

                    }
                }
            } else {
                it.movie?.let {
                    items(it) {
                        movieItem(movie = it)
                    }
                }

            }
        }
    }
}

@Composable
fun header(baseMovieData: BaseMovieData, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = baseMovieData.header,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.weight(1f))
        OutlinedButton(
            onClick = { onClick() },
            modifier = Modifier
                .wrapContentSize()
                .height(22.dp),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            contentPadding = PaddingValues()
        ) {
            Text(
                text = "See more",
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 12.sp,
                    color = Color.Blue
                ),
                modifier = Modifier.padding(start = 12.dp, end = 12.dp)
            )
        }
    }
}

@Composable
fun movieItem(movie: Movie) {
    Column {
        Card(
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .wrapContentHeight()
        ) {
            Column {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500" + movie.posterPath,
                    contentDescription = movie.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Text(
            text = movie.title,
            modifier = Modifier
                .padding(8.dp)
                .width(120.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Row {
            Image(Icons.Default.Star, contentDescription = "")
            Text(
                text = "${movie.voteAverage}/${movie.voteCount}",
                modifier = Modifier.padding(start = 4.dp)
            )
        }

    }
}


@Composable
fun LatestScreen() {
    Column {
        Text(text = "Latest Screen")
    }
}

@Composable
fun FavoriteScreen() {
    Column {
        Text(text = "Favorite Screen")
    }
}

@Preview("movie screen", widthDp = 250, heightDp = 300)
@Composable
fun MovieItemPreview() {
    Surface {
        for (i in 0 until 15) {
            movieItem(
                Movie(
                    adult = false,
                    "/3V4kLQg0kSqPLctI5ziYWabAZYF.jpg",
                    listOf(28, 878, 12),
                    id = 912649,
                    "en",
                    "Venom: The Last Dance",
                    overview = "Eddie and Venom are on the run. Hunted by both of their worlds and with the net closing in, the duo are forced into a devastating decision that will bring the curtains down on Venom and Eddie's last dance.",
                    popularity = 12656.263,
                    "/aosm8NMQ3UyoBVpSxyimorCQykC.jpg",
                    "2024-10-22",
                    title = "Venom: The Last Dance",
                    video = false,
                    6.6,
                    1121
                )
            )
        }
    }
}

@Preview("home screen")
@Composable
fun HomeNewScreenPreview() {
    Surface {
        HomeNewScreen(HomeViewModel(movieApi = null))
    }
}