package com.example.sakhcast.ui.profile_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sakhcast.CURRENT_VERSION
import com.example.sakhcast.Colors
import com.example.sakhcast.R
import com.example.sakhcast.TELEGRAM_URL
import com.example.sakhcast.data.browserIntent
import com.example.sakhcast.ui.log_in_screen.LogInScreenViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onDismissRequest: () -> Unit,
    logInScreenViewModel: LogInScreenViewModel = hiltViewModel(),
    avatar: Painter,
) {
    val profileScreenState = logInScreenViewModel.userDataState.observeAsState(
        LogInScreenViewModel.UserDataState()
    )
    val currentUser = profileScreenState.value.currentUser
    val appVersionsListState by logInScreenViewModel.appVersionsList.observeAsState()
    val skipPartiallyExpanded by rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var isExposed by remember {
        mutableStateOf(false)
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = bottomSheetState,
        scrimColor = Color(0x00000000)
    ) {
        Column {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    IconButton(onClick = { context.browserIntent(TELEGRAM_URL) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_telegram),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    }
                }
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(text = "Учетная запись")
                }
                Text(
                    text = "Готово",
                    color = Colors.blueColor,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .clickable {
                            scope.launch {
                                bottomSheetState.hide()
                                onDismissRequest()
                            }
                        }
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(100.dp)
                            .clip(CircleShape),
                        painter = avatar,
                        contentDescription = null
                    )
                    Column(
                        modifier = Modifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (currentUser != null) {
                            Text(text = currentUser.login)
                            Spacer(modifier = Modifier.height(15.dp))
                            Text(
                                text = currentUser.proDays.toString() + "дн.",
                                color = Color.Black,
                                modifier = Modifier
                                    .clip(MaterialTheme.shapes.small)
                                    .background(Colors.proDaysCountGreenColor)
                                    .padding(4.dp)
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier.padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(
                    shape = RoundedCornerShape(4.dp),
                    onClick = { isExposed = true },
                    colors = ButtonColors(
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        containerColor = MaterialTheme.colorScheme.inverseOnSurface,
                        disabledContentColor = MaterialTheme.colorScheme.inverseOnSurface,
                        disabledContainerColor = MaterialTheme.colorScheme.inverseOnSurface
                    )
                ) {
                    Text(text = "Версии")
                }

                DropdownMenuContainer(
                    expanded = isExposed,
                    onDismiss = { isExposed = false }
                ) {
                    Column {
                        appVersionsListState?.appVersionResponse?.forEach { version ->
                            DropdownMenuItem(
                                onClick = {
                                    logInScreenViewModel.downloadApk(
                                        version.url,
                                        version.filename,
                                    )
                                    isExposed = false
                                }
                            ) {
                                Text(text = "Скачать ${version.version}")
                            }
                        }
                    }
                }
            }

            Text(
                text = "Текущая версия: $CURRENT_VERSION",
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(
                text = "Последняя доступная версия: ${
                    appVersionsListState?.appVersionResponse?.get(
                        0
                    )?.version
                }",
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            TextButton(
                onClick = {
                    logInScreenViewModel.onLogoutButtonPushed()
                }, modifier = Modifier
                    .padding(16.dp)
                    .clip(MaterialTheme.shapes.small)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
            ) {
                Text(text = "Выйти из аккаунта", color = Colors.blueColor)
            }
            Spacer(modifier = Modifier.height(200.dp))
        }
    }
}

@Composable
fun DropdownMenuContainer(
    expanded: Boolean,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier.wrapContentSize(),
        contentAlignment = Alignment.TopEnd,
    ) {
        DropdownMenu(
            modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface),
            expanded = expanded,
            onDismissRequest = onDismiss
        ) {
            content()
        }
    }
}