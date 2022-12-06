package com.thoughtworks.ark.sample.permission.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.thoughtworks.ark.core.permission.PermissionHandler
import com.thoughtworks.ark.sample.R
import com.thoughtworks.ark.ui.component.AppFilledButton
import com.thoughtworks.ark.ui.theme.Dimensions
import com.thoughtworks.ark.ui.theme.LocalShapes
import com.thoughtworks.ark.ui.theme.Theme

@Composable
internal fun PermissionPlaceHolder(permissionHandler: PermissionHandler) {
    var showDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        AppFilledButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            onClick = { showDialog = true },
            text = {
                Text(text = "Click to request location permission")
            }
        )
    }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .background(Theme.colors.background, shape = LocalShapes.current.large)
            ) {
                Column(
                    modifier = Modifier
                        .height(300.dp)
                        .align(Alignment.Center)
                        .padding(Dimensions.dimension16),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(Color.Gray),
                        modifier = Modifier.padding(top = Dimensions.dimension16)
                    )
                    CommonText(
                        modifier = Modifier.padding(top = Dimensions.dimension16),
                        text = stringResource(R.string.request_permission_dialog_content)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = Dimensions.dimension20),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        AppFilledButton(
                            modifier = Modifier.width(130.dp),
                            onClick = { showDialog = false },
                            text = {
                                Text(text = stringResource(R.string.action_label_cancel))
                            }
                        )

                        AppFilledButton(
                            modifier = Modifier.width(130.dp),
                            onClick = {
                                showDialog = false
                                permissionHandler.requestPermission()
                            },
                            text = {
                                Text(text = stringResource(R.string.action_label_ok))
                            }
                        )
                    }
                }
            }
        }
    }
}