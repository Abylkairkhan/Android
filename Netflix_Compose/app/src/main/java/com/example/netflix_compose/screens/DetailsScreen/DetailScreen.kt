package com.example.netflix_compose.screens.DetailsScreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import com.example.netflix_compose.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailScreen(movie_id: Int) {
    val scroll: ScrollState = rememberScrollState(0)
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        details(scroll)
    }
}


@Composable
fun details(scroll: ScrollState) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Header()
        Body(scroll)
        Toolbar()
        Title()
    }
}

@Preview(showBackground = true)
@Composable
fun preview1() {
    Header()
}

@Preview(showBackground = true)
@Composable
fun preview2() {
    Body(scroll = rememberScrollState(0))
}

@Preview(showBackground = true)
@Composable
fun preview3() {
    Toolbar()
}

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(620.dp)
            .background(Color.White)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(650.dp)
                .blur(64.dp),
            painter = painterResource(id = com.example.netflix_compose.R.drawable.wick),
            contentDescription = "ImageBlur"
        )

        Box(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Image(
                modifier = Modifier
                    .width(320.dp)
                    .height(520.dp),
                painter = painterResource(id = com.example.netflix_compose.R.drawable.wick),
                contentDescription = "Image",
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Composable
fun Body(scroll: ScrollState) {
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
            repeat(10) {
                Text(
                    modifier = Modifier
                        .padding(top = 50.dp),
                    text = "Jon Wick 4",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Row(
                    modifier = Modifier
                        .padding(top = 5.dp)
                ) {
                    Text(
                        text = "4.2",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.error
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(horizontal = 4.dp),
                        text = "Original Title",
                        fontWeight = FontWeight.W400
                    )
                }
                Row(
                    modifier = Modifier
                ) {
                    Text(
                        text = "2023-05-11" + ",",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        color = MaterialTheme.colorScheme.outline
                    )

                    val list = listOf("Comedy", "War", "Action")
                    Row {
                        for (item in list) {
                            Text(
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(start = 5.dp),
                                text = item,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W400,
                                color = MaterialTheme.colorScheme.outline
                            )
                        }
                    }
                }
                Row {
                    Text(
                        text = "USA" + ",",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        color = MaterialTheme.colorScheme.outline
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 3.dp),
                        text = "n" + " hours" + " m" + " minutes,",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        color = MaterialTheme.colorScheme.outline
                    )
                    var bool = false
                    var text = "6+"
                    if (bool) text = "18+"
                    Text(
                        modifier = Modifier
                            .padding(start = 3.dp),
                        text = text,
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
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar() {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Green),
        modifier = Modifier
            .background(Color.Red)
            .height(60.dp),
        navigationIcon = {
            IconButton(
                modifier = Modifier,
                onClick = { /*TODO*/ }
            ) {
                Column(
                    verticalArrangement = Arrangement.Center
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

        },

        )
}

@Composable
fun Title() {
}
