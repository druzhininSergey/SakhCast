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
import java.util.Base64
import javax.inject.Inject

@HiltViewModel
class LogInScreenViewModel @Inject constructor(private val sharedPreferences: SharedPreferences) :
    ViewModel() {

    private var _userDataState = MutableLiveData(UserDataState())
    val userDataState: LiveData<UserDataState> = _userDataState

    init {
        val token = generateToken()
        _userDataState.value = userDataState.value?.copy(userToken = token)
//        Log.i("!!!", "token = $token")
//        Log.i("!!!", "stateToken = $token")
    }

    data class UserDataState(
        var userToken: String = "",
        var userLogin: String = "",
        var userPassword: String = "",
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
            val isLogged = Samples.isLogin(loginInput, passwordInput)
            _userDataState.value = userDataState.value?.copy(
                isLogged = isLogged,
            )
            saveIsLoggedInSharedPreferences(isLogged)
        }
    }

    private fun saveIsLoggedInSharedPreferences(isLogged: Boolean) {
        sharedPreferences.edit().putBoolean(IS_LOGGED_IN_KEY, isLogged).apply()
//        Log.i("!!!", "LogInScreenVM isLoggedState = ${userDataState.value?.isLogged}")
//        Log.i("!!!", "shared prefs saved in loginScreenVM ${getIsLoggedInSharedPreferences()}")
    }

    private fun getIsLoggedInSharedPreferences(): Boolean {
        return sharedPreferences.getBoolean(IS_LOGGED_IN_KEY, false) // по умолчанию false
    }
}