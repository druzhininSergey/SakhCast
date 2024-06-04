package com.example.sakhcast.ui.profile_screen

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sakhcast.IS_LOGGED_IN_KEY
import com.example.sakhcast.data.UserSample
import com.example.sakhcast.model.CurrentUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(private val sharedPreferences: SharedPreferences) :
    ViewModel() {

    private val _profileScreenState = MutableLiveData(ProfileScreenState())
    val profileScreenState: LiveData<ProfileScreenState> = _profileScreenState

    init {
        viewModelScope.launch {
            _profileScreenState.value =
                profileScreenState.value?.copy(
                    currentUser = UserSample.getUserInfo(),
                    isLogged = getIsLoggedInSharedPreferences()
                )
            Log.i("!!!", "init isLoggedState = ${getIsLoggedInSharedPreferences()}")
        }
    }

    data class ProfileScreenState(
        var currentUser: CurrentUser? = null,
        var isLogged: Boolean? = null
    )

    fun onLogoutButtonPushed() {
        viewModelScope.launch {
            saveIsLoggedInSharedPreferences(false)
//        val isLogged = getIsLoggedInSharedPreferences()
            val x = checkSharedPreferencesInitialization()
            Log.i("!!!", "sharedPrefs isExists = $x")
            _profileScreenState.value = profileScreenState.value?.copy(
                isLogged = false
            )
        }
    }

    fun saveIsLoggedInSharedPreferences(isLogged: Boolean) {
        sharedPreferences.edit().putBoolean(IS_LOGGED_IN_KEY, isLogged).apply()
    }

    fun getIsLoggedInSharedPreferences(): Boolean {
        val isLogged = sharedPreferences.getBoolean(IS_LOGGED_IN_KEY, false)
        _profileScreenState.value = profileScreenState.value?.copy(isLogged = isLogged)
        return isLogged
    }

    fun checkSharedPreferencesInitialization(): Boolean {
        return sharedPreferences.contains(IS_LOGGED_IN_KEY)
    }

}