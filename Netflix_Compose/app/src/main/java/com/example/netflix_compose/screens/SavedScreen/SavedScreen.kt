package com.example.netflix_compose.screens.SavedScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.netflix.models.Movie
import com.example.netflix_compose.Credentials
import com.example.netflix_compose.R
import com.example.netflix_compose.screens.DetailsScreen.DetailState
import com.example.netflix_compose.screens.DetailsScreen.DetailViewModel
import com.example.netflix_compose.screens.HomeScreen.*
import com.example.netflix_compose.ui.theme.Netflix_ComposeTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun SavedScreen(
    navController: NavController
){
    Netflix_ComposeTheme {
        val viewModel = getViewModel<SavedViewModel>()
        val state by viewModel.state.collectAsState()

        when (state) {
            is SavedState.Empty -> Unit
            is SavedState.ShowMovies -> showMovie(
                list = (state as SavedState.ShowMovies).movies,
                viewModel = viewModel,
                navController = navController)
        }
    }
}

@Composable
fun showMovie(
    list: List<Movie>,
    viewModel: SavedViewModel,
    navController: NavController
) {
    Netflix_ComposeTheme {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            itemsIndexed(list) { index, item ->
                if (index >= list.size - 4) {
//                    viewModel.obtainEvent(SavedEvent)
                }
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Item(item = item, navController = navController, viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun Item(
    viewModel: SavedViewModel,
    item: Movie,
    navController: NavController
) {
    Netflix_ComposeTheme {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
                .clickable { onClick(viewModel, item, navController) },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondary,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 30.dp
            ),

            ) {
            Row() {
                AsyncImage(
                    modifier = Modifier
                        .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 5.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    model = Credentials.IMAGE_URL.plus(item.poster_path),
                    contentDescription = "Image"
                )
                Column(
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 10.dp)
                ) {
                    Text(
                        modifier = Modifier,
                        text = item.title,
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 6.dp)
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(20.dp),
                            painter = painterResource(id = R.drawable.icon_star),
                            contentDescription = null
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 5.dp),
                            text = item.vote_average.toString()
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 6.dp)
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(20.dp),
                            painter = painterResource(id = R.drawable.icon_date),
                            contentDescription = null
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 5.dp),
                            text = item.release_date
                        )
                    }
                }
            }
        }
    }
}

private fun onClick(viewModel: SavedViewModel, item: Movie, navController: NavController) {
    viewModel.obtainEvent(SavedEvent.DetailsEvent(item.id!!, navController, true))
}