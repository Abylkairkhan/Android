package com.example.sneakerstreet.util.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sneakerstreet.R

@Composable
fun CollapsingToolbar(
    username: String,
    modifier: Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(6.dp)
        ) {
            Text(
                text = stringResource(id = R.string.hello) + " " + username,
                fontSize = 30.sp,
                modifier = Modifier
                    .wrapContentSize()
            )
            AsyncImage(
                model = "https://e-history.kz/storage/tmp/resize/prominent_figures/1200_0_a972c49eebd581ed84762d2f5a873313.jpg",
                contentDescription = "User Image",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .padding(6.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewCollapsingToolbar(

) {
    CollapsingToolbar(
        "Abylkairkhan",
        modifier = Modifier
            .fillMaxWidth()
            .height(158.dp)
    )
}