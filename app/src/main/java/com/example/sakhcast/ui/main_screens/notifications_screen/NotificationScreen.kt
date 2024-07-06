package com.example.sakhcast.ui.main_screens.notifications_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.twotone.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sakhcast.Colors
import com.example.sakhcast.Dimens

@Preview
@Composable
fun PreviewNotificationScreen() {
//    NotificationScreen(
//        paddingValues = PaddingValues(20.dp),
//        notificationScreenState = notificationScreenState
//    )
}

@Composable
fun NotificationScreen(
    paddingValues: PaddingValues,
    notificationScreenState: State<NotificationScreenViewModel.NotificationScreenState>,
    makeAllNotificationsRead: () -> Unit
) {
    val notificationList = notificationScreenState.value.notificationsList?.items

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.primary),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary),
        ) {
            if (notificationList != null)
                itemsIndexed(notificationList) { index, notification ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(start = Dimens.mainPadding, top = 3.dp, bottom = 3.dp)
                                .widthIn(max = 350.dp),
                            text = notification.text.substringBefore("<br>"),
                            fontSize = 12.sp
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            tint = if (!notification.acknowledge) Colors.blueColor else MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    if (index in 0 until notificationList.size - 1) {
                        HorizontalDivider(
                            modifier = Modifier
                                .padding(start = Dimens.mainPadding, end = Dimens.mainPadding),
                            thickness = 1.dp
                        )
                    }
                }
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
            FloatingActionButton(
                onClick = makeAllNotificationsRead,
                containerColor = Color.DarkGray,
                modifier = Modifier.padding(20.dp),
                elevation = FloatingActionButtonDefaults.elevation(10.dp)
            ) {
                Icon(
                    imageVector = Icons.TwoTone.Check,
                    contentDescription = null,
                )
            }
        }
    }

}