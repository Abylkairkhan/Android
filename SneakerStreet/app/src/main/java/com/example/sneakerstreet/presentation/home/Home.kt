package com.example.sneakerstreet.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sneakerstreet.domain.models.Product
import com.example.sneakerstreet.util.event.HomeEvent
import com.example.sneakerstreet.util.state.HomeState
import com.example.sneakerstreet.util.ui.CollapsingToolbar
import com.example.sneakerstreet.util.ui.ItemCard


val list = listOf<Product>(
    Product(
        id = "123",
        name = "SAMBA VEGAN",
        price = 120000,
        category = "Sinkers",
        image = listOf(
            "photo_2023-09-14_21-33-25.jpg?alt=media&token=2ff65b7d-9839-4da2-928b-7c2fab8770c2",
            "photo_2023-09-14_21-33-28.jpg?alt=media&token=5eb03cff-afbd-4cb6-a7b2-8398f990ff07"
        ),
        color = "Black"
    ),
    Product(
        id = "123",
        name = "Nike Air Force 1",
        price = 90000,
        category = "Sinkers",
        image = listOf(
            "photo_2023-09-14_21-57-32.jpg?alt=media&token=1503f8ab-316c-42c2-b29f-b4aba238ef0f",
            "photo_2023-09-14_21-57-34.jpg?alt=media&token=a69e8fe2-a72d-4d8d-848d-7635b8fc30fa"
        ),
        color = "Blue"
    ),
    Product(
        id = "123",
        name = "SAMBA VEGAN",
        price = 120000,
        category = "Sinkers",
        image = listOf(
            "photo_2023-09-14_21-33-25.jpg?alt=media&token=2ff65b7d-9839-4da2-928b-7c2fab8770c2",
            "photo_2023-09-14_21-33-28.jpg?alt=media&token=5eb03cff-afbd-4cb6-a7b2-8398f990ff07"
        ),
        color = "Black"
    ),
    Product(
        id = "123",
        name = "Nike Air Force 1",
        price = 90000,
        category = "Sinkers",
        image = listOf(
            "photo_2023-09-14_21-57-32.jpg?alt=media&token=1503f8ab-316c-42c2-b29f-b4aba238ef0f",
            "photo_2023-09-14_21-57-34.jpg?alt=media&token=a69e8fe2-a72d-4d8d-848d-7635b8fc30fa"
        ),
        color = "Blue"
    ),
    Product(
        id = "123",
        name = "SAMBA VEGAN",
        price = 120000,
        category = "Sinkers",
        image = listOf(
            "photo_2023-09-14_21-33-25.jpg?alt=media&token=2ff65b7d-9839-4da2-928b-7c2fab8770c2",
            "photo_2023-09-14_21-33-28.jpg?alt=media&token=5eb03cff-afbd-4cb6-a7b2-8398f990ff07"
        ),
        color = "Black"
    ),
    Product(
        id = "123",
        name = "Nike Air Force 1",
        price = 90000,
        category = "Sinkers",
        image = listOf(
            "photo_2023-09-14_21-57-32.jpg?alt=media&token=1503f8ab-316c-42c2-b29f-b4aba238ef0f",
            "photo_2023-09-14_21-57-34.jpg?alt=media&token=a69e8fe2-a72d-4d8d-848d-7635b8fc30fa"
        ),
        color = "Blue"
    ),
    Product(
        id = "123",
        name = "SAMBA VEGAN",
        price = 120000,
        category = "Sinkers",
        image = listOf(
            "photo_2023-09-14_21-33-25.jpg?alt=media&token=2ff65b7d-9839-4da2-928b-7c2fab8770c2",
            "photo_2023-09-14_21-33-28.jpg?alt=media&token=5eb03cff-afbd-4cb6-a7b2-8398f990ff07"
        ),
        color = "Black"
    ),
    Product(
        id = "123",
        name = "Nike Air Force 1",
        price = 90000,
        category = "Sinkers",
        image = listOf(
            "photo_2023-09-14_21-57-32.jpg?alt=media&token=1503f8ab-316c-42c2-b29f-b4aba238ef0f",
            "photo_2023-09-14_21-57-34.jpg?alt=media&token=a69e8fe2-a72d-4d8d-848d-7635b8fc30fa"
        ),
        color = "Blue"
    ),
    Product(
        id = "123",
        name = "SAMBA VEGAN",
        price = 120000,
        category = "Sinkers",
        image = listOf(
            "photo_2023-09-14_21-33-25.jpg?alt=media&token=2ff65b7d-9839-4da2-928b-7c2fab8770c2",
            "photo_2023-09-14_21-33-28.jpg?alt=media&token=5eb03cff-afbd-4cb6-a7b2-8398f990ff07"
        ),
        color = "Black"
    ),
    Product(
        id = "123",
        name = "Nike Air Force 1",
        price = 90000,
        category = "Sinkers",
        image = listOf(
            "photo_2023-09-14_21-57-32.jpg?alt=media&token=1503f8ab-316c-42c2-b29f-b4aba238ef0f",
            "photo_2023-09-14_21-57-34.jpg?alt=media&token=a69e8fe2-a72d-4d8d-848d-7635b8fc30fa"
        ),
        color = "Blue"
    ),
    Product(
        id = "123",
        name = "SAMBA VEGAN",
        price = 120000,
        category = "Sinkers",
        image = listOf(
            "photo_2023-09-14_21-33-25.jpg?alt=media&token=2ff65b7d-9839-4da2-928b-7c2fab8770c2",
            "photo_2023-09-14_21-33-28.jpg?alt=media&token=5eb03cff-afbd-4cb6-a7b2-8398f990ff07"
        ),
        color = "Black"
    ),
    Product(
        id = "123",
        name = "Nike Air Force 1",
        price = 90000,
        category = "Sinkers",
        image = listOf(
            "photo_2023-09-14_21-57-32.jpg?alt=media&token=1503f8ab-316c-42c2-b29f-b4aba238ef0f",
            "photo_2023-09-14_21-57-34.jpg?alt=media&token=a69e8fe2-a72d-4d8d-848d-7635b8fc30fa"
        ),
        color = "Blue"
    ),
)

private val MinToolbarHeight = 0.dp
private val MaxToolbarHeight = 108.dp

@Composable
fun Home(
    state: HomeState,
    obtainEvent: (HomeEvent) -> Unit,
    navController: NavHostController,
) {
    obtainEvent(HomeEvent.LoadData)

    val toolbarHeightRange = with(LocalDensity.current) {
        MinToolbarHeight.roundToPx()..MaxToolbarHeight.roundToPx()
    }

    val listState = rememberLazyListState()

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                return Offset.Zero
            }
        }
    }

    CollapsingToolbar(
        username = "Adikow",
        modifier = Modifier
            .fillMaxWidth()
            .height(MaxToolbarHeight)
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer { translationY = toolbarHeightRange.last.toFloat() },
    ) {
        items(list.size) {
            ItemCard(
                state,
                obtainEvent,
                navController,
                list[it]
            )
        }
    }
}

@Preview
@Composable
fun PreviewHome() {
    Home(
        state = HomeState(),
        obtainEvent = {},
        navController = rememberNavController(),
    )
}