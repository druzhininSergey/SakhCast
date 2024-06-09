package com.example.sakhcast.data.repository

import android.util.Log
import com.example.sakhcast.data.api_service.SackCastApiService
import com.example.sakhcast.model.CurentUser
import com.example.sakhcast.model.LoginResponse
import com.example.sakhcast.model.ResultLogout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SakhCastRepository @Inject constructor(
    private val sackCastApiService: SackCastApiService,
    private val json: Json
) {
    private val ioDispatcher: CoroutineContext = Dispatchers.IO

    suspend fun userLogin(loginInput: String, passwordInput: String): LoginResponse? {
        return withContext(ioDispatcher) {
            try {
                val loginCall = sackCastApiService.userLogin(loginInput, passwordInput)
                val responseBody = loginCall.execute()
                Log.i("!!!", "Login response body: ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun userLogout(): ResultLogout? {
        return withContext(ioDispatcher) {
            try {
                val logoutCall = sackCastApiService.userLogout()
                val responseBody = logoutCall.execute()
                Log.i("!!!", "Logout response body: ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
                Log.i("!!!", "Logout exception = null запрос не отправлен")
                null
            }
        }
    }
    suspend fun checkLoginStatus(): CurentUser? {
        return withContext(ioDispatcher) {
            try {
                val loginStatusCall = sackCastApiService.checkLoginStatus()
                val responseBody = loginStatusCall.execute()
                Log.i("!!!", "userCheck = ${responseBody.body()}")
                responseBody.body()
            } catch (e: Exception) {
                null
            }
        }
    }

}