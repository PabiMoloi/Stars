package com.example.stars.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stars.model.SignDesc
import com.example.stars.view.components.SignCategoryChip
import com.example.stars.view.ui.theme.StarsTheme
import com.example.stars.viewmodel.SignSelectionViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignSelectionActivity : ComponentActivity() {

    private val DEFAULT_SIGN_VALUE = "sign"

    private val viewModel: SignSelectionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            startObservers()

            val sign = getSelectedSign(this)
            if (sign == DEFAULT_SIGN_VALUE) {
                val signs = viewModel.signs.observeAsState()
                StarsTheme {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally, content = {
                            Surface(color = MaterialTheme.colors.background) {
                                SelectStarSign("Select Your Star Sign")
                            }
                            StarSignList(starSigns = signs.value!!) {
                                saveSelectedSign(it, applicationContext)
                                loadHomeActivity()
                            }
                        }
                    )
                }
            } else {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }

        }
    }

    private fun loadHomeActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun startObservers() {
        viewModel.getSigns()
    }
}

@Composable
fun SelectStarSign(instruction: String) {
    Text(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp), text = instruction)
}

@Composable
fun StarSignList(starSigns: List<SignDesc>, onSignSelect: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(starSigns) {
            Surface(modifier = Modifier.clickable { onSignSelect(it.sign) }) {
                val imageResource: Int = LocalContext.current.resources.getIdentifier(
                    it.image,
                    "drawable",
                    LocalContext.current.packageName
                )
                SignCategoryChip(sign = it.sign, dateRange = it.dateRange, image = imageResource)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StarsTheme {
        Column {
            SelectStarSign("Select Your Star Sign")
            SignCategoryChip(sign = "Scorpio", dateRange = "October 20 - November 22", image = 1)
        }

    }
}