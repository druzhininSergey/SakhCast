package com.example.sakhcast.ui.main_screens.notifications_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sakhcast.data.repository.SakhCastRepository
import com.example.sakhcast.model.NotificationList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationScreenViewModel @Inject constructor(private val sakhCastRepository: SakhCastRepository) :
    ViewModel() {

    private var _notificationScreenState = MutableLiveData(NotificationScreenState())
    val notificationScreenState: LiveData<NotificationScreenState> = _notificationScreenState

    init {
        getNotifications()
    }

    data class NotificationScreenState(
        var notificationsList: NotificationList? = null
    )

    private fun getNotifications() {
        viewModelScope.launch {
            val notificationList = sakhCastRepository.getNotificationsList()
            _notificationScreenState.value =
                notificationScreenState.value?.copy(notificationsList = notificationList)
        }
    }

    fun makeAllNotificationsRead(){
        viewModelScope.launch {
            sakhCastRepository.makeAllNotificationsRead()
            val currentNotifications = _notificationScreenState.value?.notificationsList?.items ?: return@launch
            val updatedNotifications = currentNotifications.map { it.copy(acknowledge = true) }

            _notificationScreenState.value = notificationScreenState.value?.copy(
                notificationsList = NotificationList(
                    amount = updatedNotifications.size,
                    items = updatedNotifications
                )
            )
        }
    }
}