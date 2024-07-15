package com.example.sakhcast.ui.log_in_screen

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sakhcast.SHARED_PREFS_TOKEN_KEY
import com.example.sakhcast.data.downloader.Downloader
import com.example.sakhcast.data.repository.SakhCastRepository
import com.example.sakhcast.model.AppVersionResponseItem
import com.example.sakhcast.model.CurrentUser
import com.example.sakhcast.model.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class LogInScreenViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val sakhCastRepository: SakhCastRepository,
    private val downloader: Downloader,
) : ViewModel() {

    private var _userDataState = MutableLiveData(UserDataState())
    val userDataState: LiveData<UserDataState> = _userDataState

    private var _appVersionsList = MutableLiveData(AppVersionResponse())
    val appVersionsList: LiveData<AppVersionResponse> = _appVersionsList

    init {
        getVersionsList()
    }

    data class AppVersionResponse(
        var appVersionResponse: List<AppVersionResponseItem> = emptyList()
    )

    data class UserDataState(
        var currentUser: CurrentUser? = null,
        var isLogged: Boolean? = null,
        var isPasswordCorrect: Boolean = true,
        var isUserPro: Boolean = true,
    )

    private fun generateToken(): String {
        return UUID.randomUUID().toString()
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
            val loginResponse: LoginResponse? =
                sakhCastRepository.userLogin(loginInput, passwordInput)
            if (loginResponse != null && loginResponse.user.pro) {
                val user = loginResponse.user
                _userDataState.value = userDataState.value?.copy(
                    currentUser = user,
                    isLogged = true,
                    isPasswordCorrect = true,
                    isUserPro = true
                )
            } else if (loginResponse != null && !loginResponse.user.pro) {
                _userDataState.value = userDataState.value?.copy(
                    currentUser = null,
                    isLogged = false,
                    isPasswordCorrect = true,
                    isUserPro = false
                )
                saveUserTokenInSharedPrefs("")
            } else if (loginResponse == null) {
                _userDataState.value = userDataState.value?.copy(isPasswordCorrect = false)
                saveUserTokenInSharedPrefs("")
            }
        }
    }

    fun checkLoggedUser() {
        viewModelScope.launch {
            val currentUser = sakhCastRepository.checkLoginStatus()
            if (currentUser == null) {
                saveUserTokenInSharedPrefs("")
                _userDataState.value =
                    userDataState.value?.copy(isLogged = false)
            } else {
                _userDataState.value =
                    userDataState.value?.copy(
                        currentUser = currentUser,
                        isLogged = true,
                    )
            }
        }
    }

    fun onLogoutButtonPushed() {
        viewModelScope.launch {
            sakhCastRepository.userLogout()
            saveUserTokenInSharedPrefs("")
            _userDataState.value = userDataState.value?.copy(
                isLogged = false,
            )
        }
    }

    private fun getVersionsList() {
        viewModelScope.launch {
            val versionList = sakhCastRepository.getVersionsList() ?: emptyList()
            _appVersionsList.value = appVersionsList.value?.copy(appVersionResponse = versionList)
        }
    }

    fun downloadApk(url: String, fileName: String) {
        downloader.downloadFile(url, fileName)

    }

}