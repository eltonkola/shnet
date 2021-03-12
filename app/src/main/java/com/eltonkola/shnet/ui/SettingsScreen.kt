package com.eltonkola.shnet.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eltonkola.shnet.MainViewModel
import com.eltonkola.shnet.R
import com.eltonkola.shnet.ui.theme.ShnetTheme
import com.eltonkola.shnet.ui.views.DrinkView
import com.eltonkola.shnet.ui.views.SeparatorText


@OptIn(ExperimentalMaterialApi::class)
@ExperimentalFoundationApi
@Composable
fun SettingsScreen(model: MainViewModel, onClose: () -> Unit) {

    var nrOfSteps by remember{mutableStateOf(model.lastValues.workouts.size)}
    var stepsTitle by remember{mutableStateOf(
        model.lastValues.workouts.firstOrNull()?.title?.replace(" 1", "") ?: "")}

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()) {

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "back",
                modifier = Modifier.clickable {
                    onClose()
                }
            )
            Text(
                text = "Settings",
                textAlign = TextAlign.Center,
                color = Color.Yellow,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 20.dp)
            )
        }

        SeparatorText(
            text = "Drinking",
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
        )
        Text(
            "Remove existing drinks",
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
        )
        LazyRow(
            content = {
                items(model.dailyDrinks) { drink ->
                    DrinkView(drink = drink, delete = true) {
                        model.deleteDrink(it)
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 12.dp, bottom = 0.dp),
        )
        Text(
            "Add a drink",
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
        )

        var newDrinkText by remember{mutableStateOf("Water")}
        var sizeMilliGram by remember{mutableStateOf(750)}
        var drinkIcon by remember{mutableStateOf(R.drawable.ic_bottle_water)}

        TextField(
            value = newDrinkText,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            ,
            onValueChange = {
                newDrinkText =  it
            },
            label = { Text("Drink name?") }
        )

        Text(
            "Size in ml: ",
            modifier = Modifier.fillMaxWidth( )
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
            ) {



                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "plus",
                    modifier = Modifier.clickable {
                        if(sizeMilliGram > 50){
                            sizeMilliGram -= 50
                        }
                    }
                )
                Text(
                    text = "$sizeMilliGram",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(4.dp),
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    Icons.Default.ArrowForward,
                    contentDescription = "minus",
                    modifier = Modifier.clickable {
                        sizeMilliGram += 50
                    }
                )


            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_coffee),
                tint = if(drinkIcon ==R.drawable.ic_coffee ) Color.Yellow else  Color.LightGray,
                contentDescription = "coffee",
                modifier = Modifier
                    .width(60.dp)
                    .height(36.dp)
                    .clickable {
                        drinkIcon = R.drawable.ic_coffee
                    },
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_bottle_water),
                tint = if(drinkIcon ==R.drawable.ic_bottle_water ) Color.Yellow else  Color.LightGray,
                contentDescription = "water",
                modifier = Modifier
                    .width(60.dp)
                    .height(36.dp)
                    .clickable {
                        drinkIcon = R.drawable.ic_bottle_water
                    },
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_bottle_shaker),
                tint = if(drinkIcon ==R.drawable.ic_bottle_shaker ) Color.Yellow else  Color.LightGray,
                contentDescription = "shaker",
                modifier = Modifier
                    .width(60.dp)
                    .height(36.dp)
                    .clickable {
                        drinkIcon = R.drawable.ic_bottle_shaker
                    },
            )

            Button(onClick = {
                model.addDrink(drinkIcon, sizeMilliGram, newDrinkText)
            }) {
                Text("Add")
            }

        }


        SeparatorText(
            text = "Strength workouts",
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
        )

        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
        ) {
            Text(
                "Nr of steps",
                modifier = Modifier.weight(1f)
            )
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "plus",
                modifier = Modifier.clickable {
                    if(nrOfSteps > 0){
                        nrOfSteps -= 1
                        model.saveWorkoutSteps(nrOfSteps, stepsTitle)
                    }
                }
            )
            Text(
                text = "$nrOfSteps",
                fontSize = 18.sp,
                modifier = Modifier.padding(4.dp),
                fontWeight = FontWeight.Bold
            )
            Icon(
                Icons.Default.ArrowForward,
                contentDescription = "minus",
                modifier = Modifier.clickable {
                    nrOfSteps += 1
                    model.saveWorkoutSteps(nrOfSteps, stepsTitle)
                }
            )
        }

        TextField(
            value = stepsTitle,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            onValueChange = {
                stepsTitle =  it
                model.saveWorkoutSteps(nrOfSteps, stepsTitle)
            },
            label = { Text("Workout name?") }
        )

        SeparatorText(
            text = "Reset to defaults",
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
        )
        Button(onClick = {
            model.resetAll()
            //onClose()
        },
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp).fillMaxWidth()
        ) {
            Text("Reset all")
        }


    }

}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    ShnetTheme {
        // ShnetAppView()
    }
}