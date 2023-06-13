package com.example.firstcomposeapp

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstcomposeapp.ui.theme.FirstComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstComposeAppTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}


@Composable
private fun MyApp(
    modifier: Modifier = Modifier
){

    var shouldShowScreen by rememberSaveable { mutableStateOf(true) }

    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.background
    ) {
        if(shouldShowScreen){
            OnBoardingScreen(onContinueClicked = { shouldShowScreen = false })
        }
        else{
            Greetings()
        }
    }
}

@Composable
private fun OnBoardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
){

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to the First Compose App")
        Button(
            modifier = modifier.padding(vertical = 14.dp),
            onClick = onContinueClicked
        ) {
            Text(text = "Continue")
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingScreenPreview(){
    FirstComposeAppTheme {
        OnBoardingScreen(onContinueClicked = {})
    }
}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }){
//    Column(modifier = modifier.padding(vertical = 4.dp)) {
//        for (name in names) {
//            Greeting(name = name)
//        }
//    }

    LazyColumn(modifier = modifier.padding(4.dp)){
        items(items = names) {name ->
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(
    modifier: Modifier = Modifier,
    name: String
) {

    var expanded by remember{ mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(
        color = MaterialTheme.colors.error,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = modifier.padding(24.dp)) {

            Column(
                modifier = modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))) {

                Text(
                    text = "Count: ",
                    modifier = modifier.padding(5.dp),
                    color = MaterialTheme.colors.onError
                )
                Text(
                    text = name,
                    modifier = modifier
                        .padding(5.dp),
                    style = MaterialTheme.typography.h2.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }

            Button(
                onClick = { expanded = !expanded },
            ) {
                Text(text = if(expanded) "Show less" else "Show more")
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Preview(showBackground = true, widthDp = 340)
@Composable
private fun DefaultPreview() {
    FirstComposeAppTheme {
        Greetings()
    }
}