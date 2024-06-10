package com.example.sakhcast.ui.log_in_screen

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sakhcast.IS_LOGGED_IN_KEY
import com.example.sakhcast.data.repository.SakhCastRepository
import com.example.sakhcast.model.CurentUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.security.SecureRandom
import java.util.Base64
import javax.inject.Inject

@HiltViewModel
class LogInScreenViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val sakhCastRepository: SakhCastRepository
) :
    ViewModel() {

    private var _userDataState = MutableLiveData(UserDataState())
    val userDataState: LiveData<UserDataState> = _userDataState

    init {
        viewModelScope.launch {
            val token = generateToken()
            _userDataState.value = userDataState.value?.copy(userToken = token)
            val x = getIsLoggedInDataStore()
//            Log.i("!!!", "is logged = ${x}")
        }
    }

    data class UserDataState(
        var userToken: String = "",
        var curentUser: CurentUser? = null,
        var isLogged: Boolean? = null,
    )

    fun generateToken(length: Int = 36): String {
        val random = SecureRandom()
        val bytes = ByteArray((length * 3) / 4)
        random.nextBytes(bytes)
        return Base64.getUrlEncoder().withoutPadding()
            .encodeToString(bytes)
    }

    fun checkUserData(loginInput: String, passwordInput: String) {
        viewModelScope.launch {
            val state = sakhCastRepository.userLogin(loginInput, passwordInput)
            val user = state?.user
            val isLogged = state?.user?.authorized
//            Log.i("!!!", "isLogged = $isLogged")
            _userDataState.value = userDataState.value?.copy(
                curentUser = user,
                isLogged = isLogged,
            )
            saveIsLoggedInDataStore(isLogged)
        }
    }

    fun checkLoggedUser(){
        viewModelScope.launch {
            val curentUser = sakhCastRepository.checkLoginStatus()
            _userDataState.value = userDataState.value?.copy(
                curentUser = curentUser
            )
//            Log.e("!!!", "userState = ${_userDataState.value?.curentUser}")
        }
    }

    private suspend fun saveIsLoggedInDataStore(isLogged: Boolean?) {
        if (isLogged != null) {
            dataStore.edit { preferences ->
                preferences[booleanPreferencesKey(IS_LOGGED_IN_KEY)] = isLogged
            }
//            Log.i("!!!", "datastore saved preferences")
        }
    }

    private suspend fun getIsLoggedInDataStore(): Boolean {
        val preferences = dataStore.data.map { preferences ->
//            Log.i("!!!", "datastore inside ${preferences[booleanPreferencesKey(IS_LOGGED_IN_KEY)]}")
            preferences[booleanPreferencesKey(IS_LOGGED_IN_KEY)] ?: false
        }.first()
//        Log.i("!!!", "datastore $preferences")
        return preferences
    }

    fun onLogoutButtonPushed() {
        viewModelScope.launch {
            sakhCastRepository.userLogout()
            saveIsLoggedInDataStore(false)
            _userDataState.value = userDataState.value?.copy(
                isLogged = false
            )
        }
    }
}