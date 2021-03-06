package com.eltonkola.shnet.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eltonkola.shnet.DailyRun
import com.eltonkola.shnet.R
import com.eltonkola.shnet.ui.theme.ShnetTheme


@ExperimentalFoundationApi
@Composable
fun DailyRunView(dailyRun: DailyRun, onclick:(DailyRun) -> Unit ) {



    val backgroundColor by animateColorAsState(if (dailyRun.done) selectedBackgroundColor else unselectedBackgroundColor)
    val textColor by animateColorAsState(if (dailyRun.done) selectedTextColor else unselectedTextColor)

    Card(modifier = Modifier
        .padding(start = 14.dp, end = 14.dp, bottom = 14.dp, top = 8.dp)
        .shadow(2.dp)
        .fillMaxWidth()
        .clickable {
            onclick(dailyRun)
        },
        shape = RoundedCornerShape(8.dp),
        contentColor = textColor,
        backgroundColor = backgroundColor
    ) {
        Row() {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_running),
                tint = textColor,
                contentDescription = dailyRun.title,
                modifier = Modifier
                    .padding(top = 16.dp, start = 12.dp)
                    .width(32.dp)
                    .height(32.dp),
            )

            Text(text = dailyRun.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 46.dp)
                    .padding(18.dp),
                textAlign = TextAlign.Center
            )
        }

    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DailyRunViewPreview() {
    ShnetTheme() {
        DailyRunView(DailyRun(title = "Daily run", length = 5), {})
    }
}