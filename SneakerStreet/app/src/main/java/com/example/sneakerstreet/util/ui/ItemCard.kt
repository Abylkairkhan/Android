package com.example.sneakerstreet.util.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.sneakerstreet.R
import com.example.sneakerstreet.domain.models.Product
import com.example.sneakerstreet.util.CURRENCY_SYMBOL
import com.example.sneakerstreet.util.ROUTE_IMAGE_URL
import com.example.sneakerstreet.util.event.HomeEvent
import com.example.sneakerstreet.util.state.HomeState

@Composable
fun ItemCard(
    state: HomeState,
    obtainEvent: (HomeEvent) -> Unit,
    navController: NavHostController,
    product: Product
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier
            .size(width = 180.dp, height = 250.dp)
            .padding(3.dp)
            .clickable {
                obtainEvent(HomeEvent.SelectProductByID(product.id!!, navController))
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.TopEnd
            ) {
                AsyncImage(
                    model = ROUTE_IMAGE_URL + product.image?.get(0),
                    contentDescription = "Product Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                )
                IconToggleButton(
                    checked = product.isLiked,
                    onCheckedChange = {
                        Log.d("MyLog", "click")
                        product.isLiked = !product.isLiked
                        obtainEvent(HomeEvent.LikedProduct("1"))
                    }
                ) {
                    Icon(
                        painter = if (product.isLiked) {
                            painterResource(id = R.drawable.filled_favorite_icon)
                        } else {
                            painterResource(id = R.drawable.unfilled_favorite_icon)
                        },
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(6.dp)
            ){
                Text(
                    text = product.name!!,
                    fontWeight = FontWeight(500)
                )
                Text(
                    text = CURRENCY_SYMBOL + product.price.toString(),
                )
                Text(
                    text = product.category!!,
                    fontWeight = FontWeight(300)
                )
            }
        }
    }
}

@Preview(
    name = "Card"
)
@Composable
fun PreviewItemCard() {
    ItemCard(
        state = HomeState(),
        obtainEvent = {},
        navController = rememberNavController(),
        Product(
            name = "Nike Air Force 1",
            price = 90000,
            category = "Sinkers",
            image = listOf(
                "photo_2023-09-14_21-57-32.jpg?alt=media&token=1503f8ab-316c-42c2-b29f-b4aba238ef0f",
                "photo_2023-09-14_21-57-34.jpg?alt=media&token=a69e8fe2-a72d-4d8d-848d-7635b8fc30fa"
            ),
            color = "Blue"
        )
    )
}