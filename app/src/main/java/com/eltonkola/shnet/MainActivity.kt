package com.eltonkola.shnet

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.eltonkola.shnet.ui.ShnetAppView
import com.eltonkola.shnet.ui.theme.ShnetTheme

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels{ MainViewModelFactory(baseContext) }

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShnetTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ShnetAppView(viewModel)
                }
            }
        }
    }
}
