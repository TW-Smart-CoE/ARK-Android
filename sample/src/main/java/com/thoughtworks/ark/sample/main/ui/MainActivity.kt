package com.thoughtworks.ark.sample.main.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.thoughtworks.ark.ui.theme.Dimensions
import com.thoughtworks.ark.ui.theme.Theme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val logoThoughtworks =
        """https://www.thoughtworks.com/etc.clientlibs/thoughtworks/
            |clientlibs/clientlib-site/resources/images/thoughtworks-logo.svg
        """.trimMargin()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.colors.background)
            .padding(horizontal = Dimensions.standardPadding)
    ) {
        AsyncImage(
            model = logoThoughtworks,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            contentDescription = "Thoughtworks"
        )
        Text(text = "Hello")
    }
}