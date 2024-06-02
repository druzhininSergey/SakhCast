package com.example.sakhcast.ui.top_bottom_bars.top_app_bar

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sakhcast.R
import com.example.sakhcast.model.CurrentUser
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(currentUser: CurrentUser) {
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    var skipPartiallyExpanded by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.sakh_cast)
            )
        },
        actions = {
            IconButton(onClick = { openBottomSheet = !openBottomSheet }) {
                Icon(
                    modifier = Modifier.padding(),
                    painter = painterResource(id = R.drawable.ic_accaunt),
                    contentDescription = "",
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary),
    )
    if (openBottomSheet) {

        ModalBottomSheet(
            onDismissRequest = { openBottomSheet = false },
            sheetState = bottomSheetState,
        ) {
            Column {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(text = "Учетная запись")
                    }
                    Text(
                        text = "Готово",
                        color = Color.Blue,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable {
                                scope
                                    .launch { bottomSheetState.hide() }
                                    .invokeOnCompletion {
                                        if (!bottomSheetState.isVisible) {
                                            openBottomSheet = false
                                        }
                                    }
                            }
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(MaterialTheme.shapes.small)
                        .background(Color.DarkGray)
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
                                .size(55.dp)
                                .clip(CircleShape),
                            painter = painterResource(id = R.drawable.cast),
                            contentDescription = null
                        )
                        Column(
                            modifier = Modifier.padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = currentUser.login)
                            Spacer(modifier = Modifier.height(15.dp))
                            Text(
                                text = currentUser.proDays.toString() + "дн.",
                                color = Color.Black,
                                modifier = Modifier
                                    .clip(MaterialTheme.shapes.small)
                                    .background(Color.Green)
                                    .padding(4.dp)
                            )
                        }
                    }
                }
                TextButton(
                    onClick = { /*TODO*/ }, modifier = Modifier
                        .padding(16.dp)
                        .clip(MaterialTheme.shapes.small)
                        .fillMaxWidth()
                        .background(Color.DarkGray)
                ) {
                    Text(text = "Выйти из аккаунта", color = Color.Blue)
                }
                Spacer(modifier = Modifier.height(200.dp))
            }
        }
    }
}