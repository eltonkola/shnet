package com.eltonkola.shnet.ui.views

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eltonkola.shnet.DailyDrink
import com.eltonkola.shnet.R.drawable
import com.eltonkola.shnet.ui.selectedBackgroundColor
import com.eltonkola.shnet.ui.selectedTextColor
import com.eltonkola.shnet.ui.theme.ShnetTheme
import com.eltonkola.shnet.ui.unselectedBackgroundColor
import com.eltonkola.shnet.ui.unselectedTextColor


@ExperimentalFoundationApi
@Composable
fun DrinkView(drink: DailyDrink, delete: Boolean = false, onclick:(DailyDrink) -> Unit ) {

    val backgroundColor by animateColorAsState(if(delete) unselectedBackgroundColor else if (drink.done) selectedBackgroundColor else unselectedBackgroundColor)
    val textColor by animateColorAsState(if(delete) Color.Red else  if (drink.done) selectedTextColor else unselectedTextColor)

    Card(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .shadow(2.dp)
            .clickable {
                onclick(drink)
            },
        shape = RoundedCornerShape(8.dp),
        contentColor = textColor,
        backgroundColor = backgroundColor,

        ) {
        Column(modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = ImageVector.vectorResource(id = drink.icon),
                tint = textColor,
                contentDescription = drink.title,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .width(60.dp).height(36.dp),
            )
            Text(
                modifier = Modifier
                    .padding(6.dp),
                text = drink.title,
                textAlign = TextAlign.Center,
                fontSize = 7.sp
            )
        }

    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DrinkViewPreview() {
    ShnetTheme() {
        DrinkView(DailyDrink(title = "Coffee", icon = drawable.ic_coffee, sizeMl = 250), false, {})
    }
}