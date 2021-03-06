package com.eltonkola.shnet.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eltonkola.shnet.MainViewModel
import com.eltonkola.shnet.ui.theme.ShnetTheme

val unselectedBackgroundColor = Color.DarkGray
val selectedBackgroundColor = Color.Yellow

val unselectedTextColor = Color.White
val selectedTextColor = Color.Black

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalFoundationApi
@Composable
fun TrackingScreen(model: MainViewModel) {

    Column(modifier = Modifier.fillMaxSize()) {
        SeparatorText(
            text = "Daily drinks",
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
        )


        LazyRow(
            content = {
                items(model.dailyDrinks) { drink ->
                    DrinkView(drink = drink) {
                        model.onDrinkClick(it)
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 12.dp, bottom = 0.dp),
        )

        SeparatorText(
            text = "Strength workouts",
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp)
        )

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(4.dp),
        ) {
            items(model.workoutSteps) { step ->
                WorkoutSepView(step = step) {
                    model.onWorkoutClick(it)
                }
            }
        }

        SeparatorText(
            text = "Other",
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 0.dp)
        )

        DailyRunView(model.dailyRun) {
            model.onRunClick(it)
        }
        Box( modifier =Modifier.height(70.dp)) {

        }
    }

}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun TrackingScreenPreview() {
    ShnetTheme {
        // ShnetAppView()
    }
}