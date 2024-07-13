package com.example.sakhcast.ui.main_screens.notifications_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sakhcast.data.repository.SakhCastRepository
import com.example.sakhcast.model.NotificationList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationScreenViewModel @Inject constructor(private val sakhCastRepository: SakhCastRepository) :
    ViewModel() {

    private var _notificationScreenState = MutableStateFlow(NotificationScreenState())
    val notificationScreenState = _notificationScreenState.asStateFlow()

    data class NotificationScreenState(
        var notificationsList: NotificationList? = null,
        var isLoading: Boolean = true,
    )

    fun getNotifications() {
        viewModelScope.launch {
            _notificationScreenState.update { currentState ->
                currentState.copy(isLoading = true)
            }
            try {
                val notificationList = sakhCastRepository.getNotificationsList()
                _notificationScreenState.update {
                    it.copy(notificationsList = notificationList, isLoading = false)
                }
            } catch (e: Exception) {
                _notificationScreenState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun makeAllNotificationsRead() {
        viewModelScope.launch {
            sakhCastRepository.makeAllNotificationsRead()
            val currentNotifications =
                notificationScreenState.value.notificationsList?.items ?: emptyList()
            val updatedNotifications = currentNotifications.map { it.copy(acknowledge = true) }
            _notificationScreenState.update { currentState ->
                currentState.copy(
                    notificationsList = NotificationList(
                        amount = updatedNotifications.size,
                        items = updatedNotifications
                    )
                )
            }

        }
    }
}