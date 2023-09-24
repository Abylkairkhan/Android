package com.example.netflix_compose.screens.DetailsScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.netflix.models.Movie
import com.example.netflix_compose.Credentials
import com.example.netflix_compose.R
import com.example.netflix_compose.models.Cast
import com.example.netflix_compose.screens.DetailsScreen.*
import com.example.netflix_compose.screens.HomeScreen.*
import com.example.netflix_compose.ui.theme.Netflix_ComposeTheme
import org.koin.androidx.compose.getViewModel
import kotlin.math.roundToInt

@Composable
fun DetailScreen(
    movie_id: Int,
    navController: NavController,
    type: Boolean
) {

    val viewModel = getViewModel<DetailViewModel>()
    val scroll: ScrollState = rememberScrollState(0)
    val state by viewModel.state.collectAsState()

    viewModel.obtainEvent(DetailEvent.WaitMovie(movie_id, type))

    Netflix_ComposeTheme {
        when (state) {
            is DetailState.Empty -> Unit
            is DetailState.ShowProgress -> showProgressBar()
            is DetailState.ShowMovie -> ShowDetails(
                scroll = scroll,
                movie = (state as DetailState.ShowMovie).movie,
                cast = (state as DetailState.ShowMovie).cast!!,
                viewModel = viewModel,
                navController = navController,
                type = type
            )
            is DetailState.ShowError -> {

            }
        }
    }
}

@Composable
fun Item(person: Cast){
    Text(text = person.name)
}

@Composable
fun MovieCast(cast: List<Cast>){
    LazyRow(){
        itemsIndexed(cast) { index, item ->
            Item(person = cast[index])
        }
    }
}

@Composable
fun ShowDetails(
    scroll: ScrollState,
    movie: Movie,
    cast: List<Cast>,
    viewModel: DetailViewModel,
    navController: NavController,
    type: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Header(movie)
            Body(scroll, movie, viewModel, type, cast)
            Toolbar(scroll, movie, viewModel, navController)
        }
    }
}

@Composable
fun Header(movie: Movie) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(620.dp)
            .background(Color.White)
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = Credentials.IMAGE_URL.plus(movie.poster_path)
            ),
            contentDescription = "ImageBlur",
            modifier = Modifier
                .fillMaxWidth()
                .height(650.dp)
                .blur(64.dp)
        )
        Box(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(320.dp)
                    .height(520.dp),
                model = Credentials.IMAGE_URL.plus(movie.poster_path),
                contentDescription = null
            )
        }
    }
}

@Composable
fun Body(
    scroll: ScrollState,
    movie: Movie,
    viewModel: DetailViewModel,
    type: Boolean,
    castList: List<Cast>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .height(580.dp)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {

            Text(
                modifier = Modifier
                    .width(350.dp)
                    .padding(top = 40.dp),
                text = movie.title,
                fontSize = 32.sp,
                lineHeight = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier
                    .padding(top = 5.dp)
            ) {
                val rounded = (movie.vote_average * 100.0).roundToInt() / 100.0
                Text(
                    text = rounded.toString(),
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold,
                    color =
                    if (rounded < 5)
                        colorResource(id = R.color.bad)
                    else if (rounded < 7)
                        colorResource(id = R.color.medium)
                    else
                        colorResource(id = R.color.good)
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 4.dp),
                    text = movie.original_title,
                    fontWeight = FontWeight.W400
                )
            }
            Row(
                modifier = Modifier
            ) {
                Text(
                    text = movie.release_date + ",",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.outline
                )

                val list = movie.genres
                Row {
                    for (index in 0..list.size) {
                        if (index < 3 && index < list.size) {
                            Text(
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(start = 5.dp),
                                text = list[index].name + ",",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W400,
                                color = MaterialTheme.colorScheme.outline
                            )
                        }
                    }
                }
            }
            Row {
                val hour = movie.runtime / 60
                val minutes = movie.runtime % 60
                Text(
                    text = movie.original_language.uppercase() + ",",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.outline
                )
                Text(
                    modifier = Modifier
                        .padding(start = 3.dp),
                    text = "$hour hours $minutes minutes,",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.outline
                )
                Text(
                    modifier = Modifier
                        .padding(start = 3.dp),
                    text = if (movie.adult) "18+" else "6+",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.outline
                )
            }
            Button(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .height(50.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.outline
                ),
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    modifier = Modifier
                        .size(28.dp)
                        .padding(horizontal = 5.dp),
                    painter = painterResource(id = R.drawable.icon_ticket),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 7.dp),
                    text = "Tickets",
                    fontWeight = FontWeight.W500
                )
            }

            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        modifier = Modifier
                            .padding(0.dp),
                        onClick = {
                            if (!type) viewModel.obtainEvent(DetailEvent.SaveMovie(movie))
                            else viewModel.obtainEvent(DetailEvent.DeleteMovie(movie))
                        }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (!type) R.drawable.icon_bookmark_add else R.drawable.icon_delete
                            ),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            contentDescription = null,
                        )
                    }
                    Text(
                        modifier = Modifier,
                        text = if (!type) "Save" else "Delete",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        modifier = Modifier
                            .padding(0.dp),
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.icon_share
                            ),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            contentDescription = null,
                        )
                    }
                    Text(
                        modifier = Modifier,
                        text = "Share",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                    )
                }
            }

            var isExpanded by remember { mutableStateOf(false) }
            Column(
                modifier = Modifier
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            ) {
                Text(
                    modifier = Modifier,
                    text = movie.overview,
                    maxLines = if (!isExpanded) 4 else Int.MAX_VALUE
                )
                TextButton(
                    modifier = Modifier
                        .padding(0.dp),
                    onClick = {
                        isExpanded = !isExpanded
                    }
                ) {
                    Text(
                        text = "All Details",
                    )
                }
            }

            MovieCast(cast = castList)

            Spacer(
                modifier = Modifier
                    .height(300.dp)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    scroll: ScrollState,
    movie: Movie,
    viewModel: DetailViewModel,
    navController: NavController
) {

    val toolbarBottom = 1440.dp
    val showToolBar by remember {
        derivedStateOf {
            scroll.value >= toolbarBottom.value
        }
    }

    AnimatedVisibility(
        visible = showToolBar,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300))
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            modifier = Modifier
                .background(Color.Red)
                .height(60.dp),
            navigationIcon = {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {

                    IconButton(
                        modifier = Modifier,
                        onClick = {
                            viewModel.obtainEvent(DetailEvent.NavigateBack(navController))
                        }
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(30.dp),
                            painter = painterResource(id = R.drawable.icon_arrowback),
                            contentDescription = null,
                        )
                    }
                }
            },
            title = {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier,
                        text = movie.title,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.W500,
                        maxLines = 1
                    )
                }
            },
        )
    }
}

