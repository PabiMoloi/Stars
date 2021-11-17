package com.example.stars.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stars.R
import com.example.stars.view.ui.theme.StarsTheme

class SplashActivity : ComponentActivity() {

    private val DEFAULT_SIGN_VALUE = "sign"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    LoadIcon()
                }
            }

            if (getSelectedSign(applicationContext) != DEFAULT_SIGN_VALUE) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, SignSelectionActivity::class.java))
                finish()
            }
        }
    }
}

@Composable
fun LoadIcon() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_noun_astrology),
            contentDescription = "astrology_icon",
            modifier = Modifier
                .height(250.dp)
                .width(250.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    StarsTheme {
        LoadIcon()
    }
}