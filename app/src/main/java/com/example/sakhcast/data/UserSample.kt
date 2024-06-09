package com.example.sakhcast.data

import com.example.sakhcast.model.CurentUser
import com.example.sakhcast.model.NewEpisodes
import com.example.sakhcast.model.Privacy

object UserSample {
    private val curentUser = CurentUser(
        access = true,
        alerts = 0,
        authorized = true,
        avatar = "https://cf2-static-eu1.stvcdn.org/img/avatars/63620397325f4-128x128.png",
        dc = 1,
        email = "mind2dima@gmail.com",
        hasPassword = true,
        id = 1563,
        login = "Dimas",
        mirror = "sakh.tv",
        newEpisodes = NewEpisodes(
            watching = 0,
            will = 0,
            stopped = 0,
            watched = 0
        ),
        notifies = 0,
        priv = true,
        privacy = Privacy(
            favShared = false
        ),
        pro = true,
        proDays = 999,
        proExpire = "2033-05-18T14:33:20+11:00",
        proSecs = 282746898,
        telegram = true,
        username = "Dimas",
        vpn = false
    )

    fun getUserInfo() = curentUser
}