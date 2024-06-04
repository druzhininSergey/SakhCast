package com.example.sakhcast.ui.log_in_screen

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sakhcast.IS_LOGGED_IN_KEY
import com.example.sakhcast.data.Samples
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.security.SecureRandom
import javax.inject.Inject

@HiltViewModel
class LogInScreenViewModel @Inject constructor(private val sharedPreferences: SharedPreferences) :
    ViewModel() {

    private var _userDataState = MutableLiveData(UserDataState())
    val userDataState: LiveData<UserDataState> = _userDataState

    init {
        val token = generateToken()
        _userDataState.value = userDataState.value?.copy(userToken = token)
    }

    data class UserDataState(
        val userToken: String = "",
        val userLogin: String = "",
        val userPassword: String = "",
        val isLogged: Boolean? = null,
    )

    private fun generateToken(): String {
        val random = SecureRandom()
        val token = CharArray(36)
        for (i in token.indices) {
            token[i] = when (i) {
                8, 13, 18, 23 -> '-'
                else -> "0123456789abcdef"[random.nextInt(16)]
            }
        }
        return String(token)
    }

    fun checkUserData(loginInput: String, passwordInput: String) {
        viewModelScope.launch {
            val isLogged = Samples.isLogin(loginInput, passwordInput)
            _userDataState.value = userDataState.value?.copy(
                isLogged = isLogged,
            )
            saveIsLoggedInSharedPreferences(isLogged)
        }
    }

    fun saveIsLoggedInSharedPreferences(isLogged: Boolean) {
        sharedPreferences.edit().putBoolean(IS_LOGGED_IN_KEY, isLogged).apply()
        Log.i("!!!", "LogInScreenVM isLoggedState = ${userDataState.value?.isLogged}")
        Log.i("!!!", "shared prefs saved in loginScreenVM ${getIsLoggedInSharedPreferences()}")
    }

    fun getIsLoggedInSharedPreferences(): Boolean {
        return sharedPreferences.getBoolean(IS_LOGGED_IN_KEY, false) // по умолчанию false
    }
}