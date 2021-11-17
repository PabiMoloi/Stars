package com.example.stars.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stars.R
import com.example.stars.model.Sign
import com.example.stars.view.ui.theme.StarsTheme
import com.example.stars.viewmodel.SignViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val viewModel: SignViewModel by viewModels()
    private lateinit var selectedSign: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedSign = getSelectedSign(this)
        setupObservers()

        setContent {
            val sign = viewModel.signInfo.observeAsState()
            val isLoading = viewModel.isLoading.observeAsState()

            StarsTheme {
                LoadingView(isLoading = isLoading.value ?: false) {
                    Surface(color = MaterialTheme.colors.background) {
                        sign.value?.let { MainView(selectedSign, it, loadImage(selectedSign)) }
                    }
                }
            }
        }
    }

    private fun setupObservers() {
        viewModel.retrieveSignInfo(selectedSign, "Today")
    }

    private fun loadImage(sign: String): Int {
        return when (sign) {
            "Aries" -> return R.drawable.ic_aries
            "Aquarius" -> R.drawable.ic_aquarius
            "Cancer" -> R.drawable.ic_cancer
            "Capricon" -> R.drawable.ic_capricon
            "Gemini" -> R.drawable.ic_gemini
            "Leo" -> R.drawable.ic_leo
            "Libra" -> R.drawable.ic_libra
            "Pisces" -> R.drawable.ic_pisces
            "Sagittarius" -> R.drawable.ic_sagittarius
            "Scorpio" -> R.drawable.ic_scorpio
            "Taurus" -> R.drawable.ic_taurus
            else -> R.drawable.ic_virgo
        }
    }
}

@Composable
fun StarSignToday(starSign: Sign) {
    Column() {
        Text(text = "Today", fontWeight = FontWeight.Bold)
        Text(text = starSign.description)
    }
}

@Composable
fun LuckyRow(starSign: Sign) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column() {
            Text(
                text = "Mood",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(text = starSign.mood, modifier = Modifier.align(Alignment.CenterHorizontally))
        }
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )

        Column {
            Text(
                text = "Lucky Number",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = starSign.lucky_number,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )

        Column {
            Text(
                text = "Lucky Time",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = starSign.lucky_time,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun LoadStarSignLogo(image: Int) {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        Icon(
            painter = painterResource(id = image),
            contentDescription = "astrology_icon",
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
        )
    }
}

@Composable
fun StarSignDescription(starSign: Sign) {
    Text(text = starSign.date_range)
    Text(text = "Compatibility: ${starSign.compatibility}")
    Text(text = "Color: ${starSign.color}")
}

@Composable
fun LoadingView(isLoading: Boolean, content: @Composable () -> Unit) = if (isLoading) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
} else {
    content()
}

@Composable
fun MainView(signName: String, starSign: Sign, image: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 8.dp, end = 8.dp, bottom = 16.dp, top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        LoadStarSignLogo(image = image)
        Text(text = "Hello $signName!", fontSize = 24.sp)
        StarSignDescription(starSign = starSign)
        LuckyRow(starSign = starSign)
        StarSignToday(starSign = starSign)
    }

}