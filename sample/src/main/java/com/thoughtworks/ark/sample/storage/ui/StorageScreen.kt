package com.thoughtworks.ark.sample.storage.ui

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thoughtworks.ark.core.extensions.showToast
import com.thoughtworks.ark.core.permission.SinglePermission
import com.thoughtworks.ark.sample.R
import com.thoughtworks.ark.sample.storage.StorageState
import com.thoughtworks.ark.sample.storage.StorageUiAction
import com.thoughtworks.ark.sample.storage.StorageUseState
import com.thoughtworks.ark.sample.storage.StorageViewModel
import com.thoughtworks.ark.ui.component.AppFilledButton
import com.thoughtworks.ark.ui.theme.Dimensions
import com.thoughtworks.ark.ui.theme.Theme

@Composable
fun StorageScreen(viewModel: StorageViewModel = viewModel()) {

    val state = viewModel.storageState.collectAsState().value
    val dispatchAction = viewModel::dispatchAction
    val context = LocalContext.current
    val versionError = stringResource(R.string.android_version_storage_error)
    val systemError = stringResource(R.string.android_system_write_remove_error)

    state.storageUseState.let { result ->
        when (result) {
            StorageUseState.None -> {}
            StorageUseState.Success -> {}
            StorageUseState.VersionError -> {
                context.showToast(versionError)
            }

            StorageUseState.SystemError -> {
                context.showToast(systemError)
            }
        }
        viewModel.clearStorageUiResult()
    }

    val contentPadding = WindowInsets.systemBars.add(
        WindowInsets(
            left = Dimensions.standardPadding,
            top = Dimensions.standardPadding,
            right = Dimensions.standardPadding,
            bottom = Dimensions.standardPadding
        )
    ).asPaddingValues()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.colors.background),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(Dimensions.standardPadding)
    ) {
        sectionButtons(dispatchAction, state)
    }
}

private fun LazyListScope.sectionButtons(
    dispatchAction: (StorageUiAction) -> Unit,
    state: StorageState,
) {
    item {
        CheckButton(dispatchAction)
        WriteButton(dispatchAction)
        RemoveButton(dispatchAction)
    }
    item {
        MyText(state.fileIsFlag)
    }
}

@Composable
private fun RemoveButton(
    dispatchAction: (StorageUiAction) -> Unit,
) {
    AppFilledButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = { dispatchAction(StorageUiAction.RemoveFileAction) },
        text = {
            Text(text = stringResource(R.string.remove_default))
        }
    )
}

@Composable
private fun WriteButton(
    dispatchAction: (StorageUiAction) -> Unit,
) {
    SinglePermission(
        permission = Manifest.permission.WRITE_EXTERNAL_STORAGE,
    ) {
        AppFilledButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { dispatchAction(StorageUiAction.WriteFileAction) },
            text = {
                Text(text = stringResource(R.string.write_default))
            }
        )
    }
}

@Composable
private fun CheckButton(
    dispatchAction: (StorageUiAction) -> Unit,
) {
    AppFilledButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = { dispatchAction(StorageUiAction.CheckAction) },
        text = {
            Text(text = stringResource(R.string.check_default))
        }
    )
}

@Composable
private fun MyText(fileIsFlag: Boolean?) {
    Row(
        modifier = Modifier.padding(
            vertical = Dimensions.dimension128, horizontal = Dimensions.dimension128
        ),
    ) {
        Text(
            buildAnnotatedString {
                withStyle(
                    style = ParagraphStyle(
                        lineHeight = 30.sp, textAlign = TextAlign.Center
                    )
                ) {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Blue, fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("check result\n")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.Blue, fontWeight = FontWeight.Bold, fontSize = 20.sp
                        )
                    ) {
                        append("is\n")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.Red, fontWeight = FontWeight.Bold, fontSize = 30.sp
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