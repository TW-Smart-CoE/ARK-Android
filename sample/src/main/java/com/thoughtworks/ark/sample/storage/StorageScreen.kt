package com.thoughtworks.ark.sample.storage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thoughtworks.ark.ui.component.AppFilledButton
import com.thoughtworks.ark.ui.theme.Dimensions
import com.thoughtworks.ark.ui.theme.Theme

@Composable
fun StorageScreen(viewModel: StorageViewModel = viewModel()) {

    val state = viewModel.storageState.collectAsState().value
    val dispatchAction = viewModel::dispatchAction

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.colors.background)
            .padding(all = Dimensions.standardPadding)
    ) {

        CheckButton(dispatchAction)
        WriteButton(dispatchAction)
        RemoveButton(dispatchAction)
        LoadImage(dispatchAction)
        MyText(state.fileIsFlag)

        state.imageBitmap?.let {
            Image(
                bitmap = it,
                contentDescription = null,
                modifier = Modifier.padding(horizontal = Dimensions.dimension128)
            )
        }

    }

}

@Composable
private fun LoadImage(
    dispatchAction: (StorageUiAction) -> Unit,
) {
    AppFilledButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = { dispatchAction(StorageUiAction.LoadImageAction) },
        text = {
            Text(text = "load image")
        }
    )
}

@Composable
private fun RemoveButton(
    dispatchAction: (StorageUiAction) -> Unit,
) {
    AppFilledButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = { dispatchAction(StorageUiAction.RemoveFileAction) },
        text = {
            Text(text = "remove default")
        }
    )
}

@Composable
private fun WriteButton(
    dispatchAction: (StorageUiAction) -> Unit,
) {
    AppFilledButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = { dispatchAction(StorageUiAction.WriteFileAction) },
        text = {
            Text(text = "write default")
        }
    )
}

@Composable
private fun CheckButton(
    dispatchAction: (StorageUiAction) -> Unit,
) {
    AppFilledButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = { dispatchAction(StorageUiAction.CheckAction) },
        text = {
            Text(text = "check default path and name")
        }
    )
}

@Composable
private fun MyText(fileIsFlag: Boolean?) {
    Row(
        modifier = Modifier.padding(
            vertical = Dimensions.dimension128,
            horizontal = Dimensions.dimension128
        ),
    ) {
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
}