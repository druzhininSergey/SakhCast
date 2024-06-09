package com.example.sakhcast.data.api_service

import com.example.sakhcast.model.CurentUser
import com.example.sakhcast.model.LoginResponse
import com.example.sakhcast.model.ResultLogout
import com.example.sakhcast.model.last_watched.LastWatched
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SackCastApiService {

    @POST("v1/users/login")
    fun userLogin(@Query("login") login: String, @Query("password") password: String): Call<LoginResponse>

    @POST("v1/users/logout")
    fun userLogout(): Call<ResultLogout>

    @GET("v2/users/current")
    fun checkLoginStatus(): Call<CurentUser>

    @GET("v2/users/continue")
    fun getContinueWatchMovieAndSerias(): Call<LastWatched>
}