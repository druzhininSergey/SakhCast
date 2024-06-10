package com.example.sakhcast.ui.log_in_screen

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sakhcast.SHARED_PREFS_TOKEN_KEY
import com.example.sakhcast.data.repository.SakhCastRepository
import com.example.sakhcast.model.CurentUser
import com.example.sakhcast.model.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.security.SecureRandom
import java.util.Base64
import javax.inject.Inject

@HiltViewModel
class LogInScreenViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val sakhCastRepository: SakhCastRepository
) : ViewModel() {

    private var _userDataState = MutableLiveData(UserDataState())
    val userDataState: LiveData<UserDataState> = _userDataState

    data class UserDataState(
//        var userToken: String = "",
        var curentUser: CurentUser? = null,
        var isLogged: Boolean? = null,
        var isPasswordCorrect: Boolean = true,
    )

    private fun generateToken(length: Int = 36): String {
        val random = SecureRandom()
        val bytes = ByteArray((length * 3) / 4)
        random.nextBytes(bytes)
        return Base64.getUrlEncoder().withoutPadding()
            .encodeToString(bytes)
    }

    private fun saveUserTokenInSharedPrefs(userToken: String) {
        with(sharedPreferences.edit()) {
            putString(SHARED_PREFS_TOKEN_KEY, userToken)
            apply()
        }
    }

    fun checkTokenExist() {
        viewModelScope.launch {
            val token = sharedPreferences.getString(SHARED_PREFS_TOKEN_KEY, "")
            if (token == "") _userDataState.value =
                userDataState.value?.copy(isLogged = false)
            else _userDataState.value = userDataState.value?.copy(isLogged = true)
        }
    }

    fun checkUserData(
        loginInput: String,
        passwordInput: String
    ) {
        viewModelScope.launch {
            val token = generateToken()
            saveUserTokenInSharedPrefs(token)
            val state: LoginResponse? = sakhCastRepository.userLogin(loginInput, passwordInput)
            Log.e("!!!", "userResponse = $state")
            if (state != null && state.user.pro) {
                val user = state.user
                _userDataState.value = userDataState.value?.copy(
                    curentUser = user,
                    isLogged = true,
                    isPasswordCorrect = true
                )
            } else if (state == null) {
                Log.i("!!!", "Сработала ветка где isPassCorr = false")
                _userDataState.value = userDataState.value?.copy(isPasswordCorrect = false)
                saveUserTokenInSharedPrefs("")
            }
        }
    }

    fun checkLoggedUser() {
        viewModelScope.launch {
//            while (true) {
            val curentUser = sakhCastRepository.checkLoginStatus()
            if (curentUser == null) {
                saveUserTokenInSharedPrefs("")
                _userDataState.value =
                    userDataState.value?.copy(isLogged = false)
//                    break
            } else {
                _userDataState.value =
                    userDataState.value?.copy(
                        curentUser = curentUser,
                        isLogged = true,
                    )
            }
//                delay(5000)
//            }
        }
    }

    fun onLogoutButtonPushed() {
        viewModelScope.launch {
            sakhCastRepository.userLogout()
            saveUserTokenInSharedPrefs("")
            _userDataState.value = userDataState.value?.copy(
                isLogged = false,
//                isPasswordCorrect = true
            )
        }
    }
}