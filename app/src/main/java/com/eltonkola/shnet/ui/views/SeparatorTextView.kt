package com.eltonkola.shnet.ui.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.eltonkola.shnet.ui.theme.ShnetTheme


@ExperimentalFoundationApi
@Composable
fun SeparatorText(text: String, modifier: Modifier = Modifier) {
    Text(
        text =text,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun SeparatorTextPreview() {
    ShnetTheme() {
        SeparatorText("Test")
    }
}