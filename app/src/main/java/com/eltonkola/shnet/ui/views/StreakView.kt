package com.eltonkola.shnet.ui.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eltonkola.shnet.R.drawable
import com.eltonkola.shnet.ui.theme.ShnetTheme


@ExperimentalFoundationApi
@Composable
fun StreakView( modifier: Modifier = Modifier, streaks: Int) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = drawable.ic_fire),
            contentDescription = "streak",
            tint = if(streaks > 0) Color.Red else Color.LightGray
        )
        Text(
            text = streaks.toString(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 2.dp)
        )
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun StreakViewPreview() {
    ShnetTheme {
        StreakView(streaks = 2)
    }
}