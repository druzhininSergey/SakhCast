package com.example.sakhcast.ui.top_bottom_bars.top_app_bar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.sakhcast.R
import com.example.sakhcast.model.CurrentUser
import com.example.sakhcast.ui.profile_screen.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(user: CurrentUser?) {
//    Log.i("!!!","user isNull?? = $user")
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    val avatarPainter: Painter =
        rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = user?.avatar)
                .apply(block = fun ImageRequest.Builder.() {
                    crossfade(true)
                    //            placeholder(R.drawable.placeholder) // Укажите ресурс-заполнитель
                    //            error(R.drawable.error) // Укажите ресурс ошибки
                }).build()
        )

    CenterAlignedTopAppBar(
        title = {
            Icon(
                painter = painterResource(id = R.drawable.ic_sakh_tv_logo),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .width(113.dp)
                    .height(41.dp)
            )
        },
        actions = {
            IconButton(onClick = { openBottomSheet = true }) {
                Image(
                    painter = avatarPainter,
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary),
    )
    if (openBottomSheet) {
        ProfileScreen(
            onDismissRequest = { openBottomSheet = false },
            avatar = avatarPainter
        )
    }
}