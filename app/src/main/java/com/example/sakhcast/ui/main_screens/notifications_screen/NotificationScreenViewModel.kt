package com.example.sakhcast.ui.main_screens.notifications_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sakhcast.data.samples.NotificationSample
import com.example.sakhcast.model.NotificationList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationScreenViewModel @Inject constructor() : ViewModel() {

    private var _notificationScreenState = MutableLiveData(NotificationScreenState())
    val notificationScreenState: LiveData<NotificationScreenState> = _notificationScreenState

    init {
        getNotifications()
    }

    data class NotificationScreenState(
        var notificationsList: NotificationList = NotificationList(0, emptyList())
    )

    fun getNotifications(){
        val notificationList = NotificationSample.getNotificationsList()
        _notificationScreenState.value = notificationScreenState.value?.copy(notificationsList = notificationList)
    }

}