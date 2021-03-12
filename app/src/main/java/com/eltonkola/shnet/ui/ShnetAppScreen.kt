package com.eltonkola.shnet.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
import com.eltonkola.shnet.ui.views.StreakView
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


@OptIn(ExperimentalMaterialApi::class)
@ExperimentalFoundationApi
@Composable
fun ShnetAppView(model: MainViewModel) {

    val formatter = SimpleDateFormat("MMMM dd yyyy")
    val formattedDate = formatter.format(Date())
    val scaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

  //  streaks


    BottomSheetScaffold(
        topBar = {
            TopAppBar(title = {
                Row(
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StreakView(
                        modifier = Modifier,
                        model.streaks
                    )
                    Text(
                        text = formattedDate,
                        textAlign = TextAlign.Center,
                        color = Color.Yellow,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_settings),
                        contentDescription = "settings",
                        modifier = Modifier.clickable {
                            coroutineScope.launch {
                                if(scaffoldState.bottomSheetState.isExpanded) {
                                    scaffoldState.bottomSheetState.collapse()
                                }else{
                                    scaffoldState.bottomSheetState.expand()
                                }
                            }
                        }
                    )
                }

            },
            elevation = 0.dp,
            modifier = Modifier.padding(top = 12.dp)
                )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { model.reset()},
                modifier = Modifier.padding(bottom = 100.dp, end = 8.dp)) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_reset),
                    tint = Color.Black,
                    contentDescription = "Reset",
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .width(60.dp)
                        .height(36.dp),
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,

        content = {
            TrackingScreen(model)
        },
        sheetContent = {
            SettingsScreen(model){
                coroutineScope.launch {
                    scaffoldState.bottomSheetState.collapse()
                }
            }
        },
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp
    )

}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShnetTheme {
       // ShnetAppView()
    }
}