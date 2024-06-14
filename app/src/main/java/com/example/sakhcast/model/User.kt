package com.example.sakhcast.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("auth") val auth: String,
    @SerialName("user") val user: CurrentUser,
)

@Serializable
data class CurrentUser(
    @SerialName("access") val access: Boolean,
    @SerialName("alerts") val alerts: Int,
    @SerialName("authorized") val authorized: Boolean,
    @SerialName("avatar") val avatar: String,
    @SerialName("dc") val dc: Int,
    @SerialName("email") val email: String,
    @SerialName("has_password") val hasPassword: Boolean,
    @SerialName("id") val id: Int,
    @SerialName("login") val login: String,
    @SerialName("mirror") val mirror: String,
    @SerialName("new_episodes") val newEpisodes: NewEpisodes,
    @SerialName("notifies") val notifies: Int,
    @SerialName("priv") val priv: Boolean,
    @SerialName("privacy") val privacy: Privacy,
    @SerialName("pro") val pro: Boolean,
    @SerialName("pro_days") val proDays: Int,
    @SerialName("pro_expire") val proExpire: String,
    @SerialName("pro_secs") val proSecs: Int,
    @SerialName("telegram") val telegram: Boolean,
    @SerialName("username") val username: String,
    @SerialName("vpn") val vpn: Boolean
)

@Serializable
data class Privacy(
    @SerialName("fav_shared") val favShared: Boolean
)

@Serializable
data class NewEpisodes(
    @SerialName("stopped") val stopped: Int,
    @SerialName("watched") val watched: Int,
    @SerialName("watching") val watching: Int,
    @SerialName("will") val will: Int
)