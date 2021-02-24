package com.eltonkola.shnet.ui.theme

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ShynetAppView() {
    Text(text = "Shneti app!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShnetTheme {
        ShynetAppView()
    }
}