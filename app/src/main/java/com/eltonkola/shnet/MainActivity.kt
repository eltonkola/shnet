package com.eltonkola.shnet

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.eltonkola.shnet.ui.theme.ShnetTheme
import com.eltonkola.shnet.ui.theme.ShynetAppView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShnetTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ShynetAppView()
                }
            }
        }
    }
}



