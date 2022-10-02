package com.example.composesquare

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.composesquare.ui.theme.ComposeSquareTheme
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {

    private var state = mutableStateOf(0)

    private val viewModel : MainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeSquareTheme {
                val number = remember { state }
                Greeting(number)
            }
        }
        val clickObserver = Observer<Int>{
            state.value = it
        }
        viewModel.clickedLiveData.observe( this, clickObserver)
    }


    @Composable
    fun Greeting(number : MutableState<Int>) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ViewGroup(number)
            SetButton(text = "Click me")
        }
    }


    @Composable
    fun ViewGroup(number : MutableState<Int>) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 80.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            SetText(number)
        }
    }

    @Composable
    fun SetText(str: MutableState<Int>) {
        Text(
            text = "${str.value}",
            modifier = Modifier.padding(end = 4.dp),
            color = Color.Green,
            fontSize = 120.sp

        )
        Text(
            text = "clicked",
            color = Color.Blue,
            fontSize = 40.sp,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .padding(bottom = 15.dp)
        )
    }

    @Composable
    fun SetButton(text: String) {
        Button(
            onClick = { viewModel.onClicked()},
            modifier = Modifier
                .padding(8.dp)
                .size(150.dp, 50.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.LightGray,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color.Yellow)
        ) {
            Text(text = text, style = TextStyle(fontSize = 24.sp))
        }
    }






    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ComposeSquareTheme {
            Greeting(number =  remember { mutableStateOf(0) })
        }
    }
}