package com.example.sakhcast.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationList(
    @SerialName("amount") val amount: Int,
    @SerialName("items") val items: List<Notification>
)

@Serializable
data class Notification(
    @SerialName("ack") var acknowledge: Boolean,
    @SerialName("context") val summary: Summary,
    @SerialName("date") val date: String,
    @SerialName("id") val id: Int,
    @SerialName("plain_text") val plainText: String,
    @SerialName("subject") val subject: String,
    @SerialName("text") val text: String,
    @SerialName("user_id") val userId: Int
)

@Serializable
data class NotificationData(
    @SerialName("episode_index") val episodeIndex: String,
    @SerialName("media_id") val mediaId: Int,
    @SerialName("rg") val releaseGroup: String,
    @SerialName("season_index") val seasonIndex: String,
    @SerialName("serial_id") val serialId: Int,
    @SerialName("serial_name") val serialName: String,
    @SerialName("serial_tvshow") val serialTvShow: String
)

@Serializable
data class Summary(
    @SerialName("action") val action: String,
    @SerialName("data") val notificationData: NotificationData
)