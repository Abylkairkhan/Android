package com.example.netflix_compose

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.netflix.models.Movie
import kotlin.math.roundToInt

@Preview
@Composable
fun Preview() {
    test()
}

@Composable
fun test() {
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl("https://developer.themoviedb.org/reference/movie-videos")
        }
    }, update = {
        it.loadUrl("https://developer.themoviedb.org/reference/movie-videos")
    })
}

@Composable
fun BlurredAsyncImage(url: String) {

    Box(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        val painter = rememberImagePainter(
            data = url,
            builder = {
                transformations()
            }
        )

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .blur(radius = 20.dp)
        )
    }
}

//BlurredAsyncImage(url = Credentials.IMAGE_URL.plus("/8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg"))


@Composable
fun Header() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(620.dp)
            .background(Color.Green)
    ) {
        AsyncImage(
            model = Credentials.IMAGE_URL.plus("/8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg"),
            contentDescription = "ImageBlur",
            modifier = Modifier
                .fillMaxWidth()
                .height(650.dp)
                .blur(15.dp),
        )
        Box(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
//            AsyncImage(
//                modifier = Modifier
//                    .width(320.dp)
//                    .height(520.dp),
//                model = Credentials.IMAGE_URL.plus("/8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg"),
//                contentDescription = null
//            )
        }
    }
}


@Composable
fun Test(scroll: ScrollState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scroll),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                text = "Spider-Man: Across the Spider-Verse",
                fontSize = 32.sp,
                lineHeight = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier
                    .padding(top = 5.dp)
            ) {
                val rounded = (8.698 * 100.0).roundToInt() / 100.0
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
                    text = "Spider-Man: Across the Spider-Verse",
                    fontWeight = FontWeight.W400
                )
            }
            Row(
                modifier = Modifier
            ) {
                Text(
                    text = "2023-05-31" + ",",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.outline
                )

                val list = listOf("Anime", "Anime", "Anime")
                Row {
                    for (index in 0..list.size) {
                        if (index < 3) {
                            Text(
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(start = 5.dp),
                                text = list[index] + ",",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W400,
                                color = MaterialTheme.colorScheme.outline
                            )
                        }
                    }
                }
            }
            Row {
                val hour = 140 / 60
                val minutes = 140 % 60
                Text(
                    text = "EN" + ",",
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
                    text = "6+",
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
//            ----------------------------------------------

            Row(
                modifier = Modifier
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        modifier = Modifier,
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.icon_bookmark_add
                            ),
                            contentDescription = null,
                            modifier = Modifier
                        )
                    }
                    Text(
                        text = "Save",
                        textAlign = TextAlign.Center,
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        modifier = Modifier,
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.icon_share
                            ),
                            contentDescription = null,
                            modifier = Modifier
                        )
                    }
                    Text(
                        modifier = Modifier,
                        text = "Share"
                    )
                }
            }
            var isExpanded by remember { mutableStateOf(false) }
            Column(
                modifier = Modifier
                    .padding(top = 20.dp, start = 20.dp)
            ) {
                Text(
                    modifier = Modifier,
                    text = "After reuniting with Gwen Stacy, Brooklyn’s full-time, friendly neighborhood Spider-Man is catapulted across the Multiverse, where he encounters the Spider Society, a team of Spider-People charged with protecting the Multiverse’s very existence. But when the heroes clash on how to handle a new threat, Miles finds himself pitted against the other Spiders and must set out on his own to save those he loves most.",
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
        }
    }
}