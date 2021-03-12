package com.eltonkola.shnet.ui.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eltonkola.shnet.WorkoutStep
import com.eltonkola.shnet.ui.selectedBackgroundColor
import com.eltonkola.shnet.ui.selectedTextColor
import com.eltonkola.shnet.ui.theme.ShnetTheme
import com.eltonkola.shnet.ui.unselectedBackgroundColor
import com.eltonkola.shnet.ui.unselectedTextColor


@ExperimentalFoundationApi
@Composable
fun WorkoutSepView(step: WorkoutStep, onclick:(WorkoutStep) -> Unit ) {
    val backgroundColor by animateColorAsState(if (step.done) selectedBackgroundColor else unselectedBackgroundColor)
    val textColor by animateColorAsState(if (step.done) selectedTextColor else unselectedTextColor)

    Card(modifier = Modifier
        .padding(6.dp)
        .shadow(2.dp)
        .clickable {
            onclick(step)
        }, shape = RoundedCornerShape(8.dp)
        , contentColor = textColor
        , backgroundColor = backgroundColor
    ) {
        Text(text = step.title,
            modifier = Modifier
                .padding(18.dp)
                .fillMaxHeight()
                .fillMaxHeight(),
            textAlign = TextAlign.Center
        )
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun WorkoutSepViewPreview() {
    ShnetTheme {
        WorkoutSepView(WorkoutStep(title = "Workout 1")){}
    }
}