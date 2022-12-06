package com.thoughtworks.ark.sample.storage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thoughtworks.ark.ui.component.AppFilledButton
import com.thoughtworks.ark.ui.theme.Dimensions
import com.thoughtworks.ark.ui.theme.Theme

@Composable
fun StorageScreen(viewModel: StorageViewModel = viewModel()) {

    val state = viewModel.storageState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.colors.background)
            .padding(all = Dimensions.standardPadding)
    ) {

        val checkValue = remember { mutableStateOf("") }
        MyTextField(
            checkValue = checkValue.value,
            onSave = {
                checkValue.value = it
            },
        )

        AppFilledButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.checkFileExist() },
            text = {
                Text(text = "check default path and name")
            }
        )

        AppFilledButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.writeFile() },
            text = {
                Text(text = "write default")
            }
        )

        AppFilledButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { },
            text = {
                Text(text = "demo 3")
            }
        )

        AppFilledButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { },
            text = {
                Text(text = "demo 4")
            }
        )

        Row(
            modifier = Modifier.padding(
                vertical = Dimensions.dimension128,
                horizontal = Dimensions.dimension128
            ),
        ) {
            MyText(state.fileIsFlag)
        }
    }

}

@Composable
private fun MyTextField(
    checkValue: String,
    onSave: (comment: String) -> Unit,
) {

    val textValue = remember(checkValue) { mutableStateOf(checkValue) }

    TextField(
        value = textValue.value,
        onValueChange = { textValue.value = it },
        placeholder = {
            Text("请输入内容")
        },
        trailingIcon = {
            Icon(
                Icons.Default.Clear,
                contentDescription = "clear text",
                modifier = Modifier
                    .clickable {
                        textValue.value = ""
                    }
            )
        }
    )
    Spacer(modifier = Modifier.width(20.dp))
    Button(onClick = { onSave.invoke(textValue.value) }) {
        Text(text = "check")
    }
}

@Composable
private fun MyText(fileIsFlag: Boolean?) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = ParagraphStyle(
                    lineHeight = 30.sp,
                    textAlign = TextAlign.Center
                )
            ) {
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("check result\n")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                ) {
                    append("is\n")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                ) {
                    if (fileIsFlag != null) {
                        append(fileIsFlag.toString())
                    }
                }
            }
        }
    )
}