package com.example.sakhcast.data.samples

import com.example.sakhcast.model.Summary
import com.example.sakhcast.model.NotificationData
import com.example.sakhcast.model.Notification
import com.example.sakhcast.model.NotificationList

object NotificationSample {
    private val notificationsList = NotificationList(
        amount = 20,
        items = listOf(
            Notification(
                acknowledge = false,
                summary = Summary(
                    action = "new_episode",
                    notificationData = NotificationData(
                        episodeIndex = "23",
                        mediaId = 234084,
                        releaseGroup = "Русский",
                        seasonIndex = "02",
                        serialId = 4671,
                        serialName = "Реинкарнация безработного (Mushoku Tensei: Jobless Reincarnation)",
                        serialTvShow = "mushoku.tensei"
                    )
                ),
                date = "2024-06-04 23:36:41",
                id = 1058895,
                plainText = "Вышел 23-й эпизод 2-го сезона сериала «Реинкарнация безработного (Mushoku Tensei: Jobless Reincarnation)» в озвучке «Русский»",
                subject = "Реинкарнация безработного. Новый эпизод!",
                text = "Вышел 23-й эпизод 2-го сезона сериала «Реинкарнация безработного» в озвучке «Русский».<br><a href='https://sakh.tv/watch/mushoku.tensei/02/23/rulang/'>Перейти к просмотру.</a>",
                userId = 1563
            ),
            Notification(
                acknowledge = false,
                summary = Summary(
                    action = "new_episode",
                    notificationData = NotificationData(
                        episodeIndex = "22",
                        mediaId = 234083,
                        releaseGroup = "Русский",
                        seasonIndex = "02",
                        serialId = 4671,
                        serialName = "Реинкарнация безработного (Mushoku Tensei: Jobless Reincarnation)",
                        serialTvShow = "mushoku.tensei"
                    )
                ),
                date = "2024-06-04 23:33:36",
                id = 1058818,
                plainText = "Вышел 22-й эпизод 2-го сезона сериала «Реинкарнация безработного (Mushoku Tensei: Jobless Reincarnation)» в озвучке «Русский»",
                subject = "Реинкарнация безработного. Новый эпизод!",
                text = "Вышел 22-й эпизод 2-го сезона сериала «Реинкарнация безработного» в озвучке «Русский».<br><a href='https://sakh.tv/watch/mushoku.tensei/02/22/rulang/'>Перейти к просмотру.</a>",
                userId = 1563
            ),
            Notification(
                acknowledge = true,
                summary = Summary(
                    action = "new_episode",
                    notificationData = NotificationData(
                        episodeIndex = "21",
                        mediaId = 234082,
                        releaseGroup = "Русский",
                        seasonIndex = "02",
                        serialId = 4671,
                        serialName = "Реинкарнация безработного (Mushoku Tensei: Jobless Reincarnation)",
                        serialTvShow = "mushoku.tensei"
                    )
                ),
                date = "2024-06-04 23:30:32",
                id = 1058741,
                plainText = "Вышел 21-й эпизод 2-го сезона сериала «Реинкарнация безработного (Mushoku Tensei: Jobless Reincarnation)» в озвучке «Русский»",
                subject = "Реинкарнация безработного. Новый эпизод!",
                text = "Вышел 21-й эпизод 2-го сезона сериала «Реинкарнация безработного» в озвучке «Русский».<br><a href='https://sakh.tv/watch/mushoku.tensei/02/21/rulang/'>Перейти к просмотру.</a>",
                userId = 1563
            ),
            Notification(
                acknowledge = false,
                summary = Summary(
                    action = "new_episode",
                    notificationData = NotificationData(
                        episodeIndex = "20",
                        mediaId = 234081,
                        releaseGroup = "Русский",
                        seasonIndex = "02",
                        serialId = 4671,
                        serialName = "Реинкарнация безработного (Mushoku Tensei: Jobless Reincarnation)",
                        serialTvShow = "mushoku.tensei"
                    )
                ),
                date = "2024-06-04 23:27:20",
                id = 1058664,
                plainText = "Вышел 20-й эпизод 2-го сезона сериала «Реинкарнация безработного (Mushoku Tensei: Jobless Reincarnation)» в озвучке «Русский»",
                subject = "Реинкарнация безработного. Новый эпизод!",
                text = "Вышел 20-й эпизод 2-го сезона сериала «Реинкарнация безработного» в озвучке «Русский».<br><a href='https://sakh.tv/watch/mushoku.tensei/02/20/rulang/'>Перейти к просмотру.</a>",
                userId = 1563
            ),
            Notification(
                acknowledge = true,
                summary = Summary(
                    action = "new_episode",
                    notificationData = NotificationData(
                        episodeIndex = "03",
                        mediaId = 233964,
                        releaseGroup = "Русский",
                        seasonIndex = "04",
                        serialId = 3370,
                        serialName = "Клинок, рассекающий демонов (Demon Slayer: Kimetsu no Yaiba)",
                        serialTvShow = "kimetsu-no-yaiba"
                    )
                ),
                date = "2024-06-01 13:11:23",
                id = 1054777,
                plainText = "Вышел 3-й эпизод 4-го сезона сериала «Клинок, рассекающий демонов (Demon Slayer: Kimetsu no Yaiba)» в озвучке «Русский»",
                subject = "Клинок, рассекающий демонов. Новый эпизод!",
                text = "Вышел 3-й эпизод 4-го сезона сериала «Клинок, рассекающий демонов» в озвучке «Русский».<br><a href='https://sakh.tv/watch/kimetsu-no-yaiba/04/03/rulang/'>Перейти к просмотру.</a>",
                userId = 1563
            ),
            Notification(
                acknowledge = true,
                summary = Summary(
                    action = "new_episode",
                    notificationData = NotificationData(
                        episodeIndex = "19",
                        mediaId = 233799,
                        releaseGroup = "Русский",
                        seasonIndex = "02",
                        serialId = 4671,
                        serialName = "Реинкарнация безработного (Mushoku Tensei: Jobless Reincarnation)",
                        serialTvShow = "mushoku.tensei"
                    )
                ),
                date = "2024-05-28 13:10:55",
                id = 1052807,
                plainText = "Вышел 19-й эпизод 2-го сезона сериала «Реинкарнация безработного (Mushoku Tensei: Jobless Reincarnation)» в озвучке «Русский»",
                subject = "Реинкарнация безработного. Новый эпизод!",
                text = "Вышел 19-й эпизод 2-го сезона сериала «Реинкарнация безработного» в озвучке «Русский».<br><a href='https://sakh.tv/watch/mushoku.tensei/02/19/rulang/'>Перейти к просмотру.</a>",
                userId = 1563
            ),
            Notification(
                acknowledge = true,
                summary = Summary(
                    action = "new_episode",
                    notificationData = NotificationData(
                        episodeIndex = "10",
                        mediaId = 233743,
                        releaseGroup = "LostFilm",
                        seasonIndex = "07",
                        serialId = 1961,
                        serialName = "Хороший доктор (The Good Doctor)",
                        serialTvShow = "the.good.doctor"
                    )
                ),
                date = "2024-05-26 14:17:48",
                id = 1048766,
                plainText = "Вышел 10-й эпизод 7-го сезона сериала «Хороший доктор (The Good Doctor)» в озвучке «LostFilm»",
                subject = "Хороший доктор. Новый эпизод!",
                text = "Вышел 10-й эпизод 7-го сезона сериала «Хороший доктор» в озвучке «LostFilm».<br><a href='https://sakh.tv/watch/the.good.doctor/07/10/lostfilm/'>Перейти к просмотру.</a>",
                userId = 1563
            ),
            Notification(
                acknowledge = true,
                summary = Summary(
                    action = "new_episode",
                    notificationData = NotificationData(
                        episodeIndex = "09",
                        mediaId = 233740,
                        releaseGroup = "RUDUB",
                        seasonIndex = "26",
                        serialId = 264,
                        serialName = "Южный Парк (South Park)",
                        serialTvShow = "south_park"
                    )
                ),
                date = "2024-05-26 14:03:37",
                id = 1048025,
                plainText = "Вышел 9-й эпизод 26-го сезона сериала «Южный Парк (South Park)» в озвучке «RUDUB»",
                subject = "Южный Парк. Новый эпизод!",
                text = "Вышел 9-й эпизод 26-го сезона сериала «Южный Парк» в озвучке «RUDUB».<br><a href='https://sakh.tv/watch/south_park/26/09/rudub/'>Перейти к просмотру.</a>",
                userId = 1563
            ),
            Notification(
                acknowledge = true,
                summary = Summary(
                    action = "new_episode",
                    notificationData = NotificationData(
                        episodeIndex = "02",
                        mediaId = 233525,
                        releaseGroup = "Русский",
                        seasonIndex = "04",
                        serialId = 3370,
                        serialName = "Клинок, рассекающий демонов (Demon Slayer: Kimetsu no Yaiba)",
                        serialTvShow = "kimetsu-no-yaiba"
                    )
                ),
                date = "2024-05-22 23:24:39",
                id = 1044146,
                plainText = "Вышел 2-й эпизод 4-го сезона сериала «Клинок, рассекающий демонов (Demon Slayer: Kimetsu no Yaiba)» в озвучке «Русский»",
                subject = "Клинок, рассекающий демонов. Новый эпизод!",
                text = "Вышел 2-й эпизод 4-го сезона сериала «Клинок, рассекающий демонов» в озвучке «Русский».<br><a href='https://sakh.tv/watch/kimetsu-no-yaiba/04/02/rulang/'>Перейти к просмотру.</a>",
                userId = 1563
            ),
            Notification(
                acknowledge = true,
                summary = Summary(
                    action = "new_episode",
                    notificationData = NotificationData(
                        episodeIndex = "14",
                        mediaId = 233522,
                        releaseGroup = "Кураж-Бомбей",
                        seasonIndex = "07",
                        serialId = 1959,
                        serialName = "Детство Шелдона (Young Sheldon)",
                        serialTvShow = "young.sheldon"
                    )
                ),
                date = "2024-05-22 19:23:00",
                id = 1043350,
                plainText = "Вышел 14-й эпизод 7-го сезона сериала «Детство Шелдона (Young Sheldon)» в озвучке «Кураж-Бомбей»",
                subject = "Детство Шелдона. Новый эпизод!",
                text = "Вышел 14-й эпизод 7-го сезона сериала «Детство Шелдона» в озвучке «Кураж-Бомбей».<br><a href='https://sakh.tv/watch/young.sheldon/07/14/kuraj-bambey/'>Перейти к просмотру.</a>",
                userId = 1563
            ),
            Notification(
                acknowledge = true,
                summary = Summary(
                    action = "new_episode",
                    notificationData = NotificationData(
                        episodeIndex = "13",
                        mediaId = 233521,
                        releaseGroup = "Кураж-Бомбей",
                        seasonIndex = "07",
                        serialId = 1959,
                        serialName = "Детство Шелдона (Young Sheldon)",
                        serialTvShow = "young.sheldon"
                    )
                ),
                date = "2024-05-22 19:21:21",
                id = 1042717,
                plainText = "Вышел 13-й эпизод 7-го сезона сериала «Детство Шелдона (Young Sheldon)» в озвучке «Кураж-Бомбей»",
                subject = "Детство Шелдона. Новый эпизод!",
                text = "Вышел 13-й эпизод 7-го сезона сериала «Детство Шелдона» в озвучке «Кураж-Бомбей».<br><a href='https://sakh.tv/watch/young.sheldon/07/13/kuraj-bambey/'>Перейти к просмотру.</a>",
                userId = 1563
            ),
            Notification(
                acknowledge = true,
                summary = Summary(
                    action = "new_episode",
                    notificationData = NotificationData(
                        episodeIndex = "12",
                        mediaId = 233299,
                        releaseGroup = "Кураж-Бомбей",
                        seasonIndex = "07",
                        serialId = 1959,
                        serialName = "Детство Шелдона (Young Sheldon)",
                        serialTvShow = "young.sheldon"
                    )
                ),
                date = "2024-05-18 23:23:49",
                id = 1037739,
                plainText = "Вышел 12-й эпизод 7-го сезона сериала «Детство Шелдона (Young Sheldon)» в озвучке «Кураж-Бомбей»",
                subject = "Детство Шелдона. Новый эпизод!",
                text = "Вышел 12-й эпизод 7-го сезона сериала «Детство Шелдона» в озвучке «Кураж-Бомбей».<br><a href='https://sakh.tv/watch/young.sheldon/07/12/kuraj-bambey/'>Перейти к просмотру.</a>",
                userId = 1563
            ),
            Notification(
                acknowledge = true,
                summary = Summary(
                    action = "new_episode",
                    notificationData = NotificationData(
                        episodeIndex = "11",
                        mediaId = 233298,
                        releaseGroup = "Кураж-Бомбей",
                        seasonIndex = "07",
                        serialId = 1959,
                        serialName = "Детство Шелдона (Young Sheldon)",
                        serialTvShow = "young.sheldon"
                    )
                ),
                date = "2024-05-18 23:21:33",
                id = 1037104,
                plainText = "Вышел 11-й эпизод 7-го сезона сериала «Детство Шелдона (Young Sheldon)» в озвучке «Кураж-Бомбей»",
                subject = "Детство Шелдона. Новый эпизод!",
                text = "Вышел 11-й эпизод 7-го сезона сериала «Детство Шелдона» в озвучке «Кураж-Бомбей».<br><a href='https://sakh.tv/watch/young.sheldon/07/11/kuraj-bambey/'>Перейти к просмотру.</a>",
                userId = 1563
            ),
            Notification(
                acknowledge = true,
                summary = Summary(
                    action = "new_episode",
                    notificationData = NotificationData(
                        episodeIndex = "10",
                        mediaId = 233297,
                        releaseGroup = "Кураж-Бомбей",
                        seasonIndex = "07",
                        serialId = 1959,
                        serialName = "Детство Шелдона (Young Sheldon)",
                        serialTvShow = "young.sheldon"
                    )
                ),
                date = "2024-05-18 23:20:05",
                id = 1036470,
                plainText = "Вышел 10-й эпизод 7-го сезона сериала «Детство Шелдона (Young Sheldon)» в озвучке «Кураж-Бомбей»",
                subject = "Детство Шелдона. Новый эпизод!",
                text = "Вышел 10-й эпизод 7-го сезона сериала «Детство Шелдона» в озвучке «Кураж-Бомбей».<br><a href='https://sakh.tv/watch/young.sheldon/07/10/kuraj-bambey/'>Перейти к просмотру.</a>",
                userId = 1563
            ),
            Notification(
                acknowledge = true,
                summary = Summary(
                    action = "new_episode",
                    notificationData = NotificationData(
                        episodeIndex = "09",
                        mediaId = 233284,
                        releaseGroup = "LostFilm",
                        seasonIndex = "07",
                        serialId = 1961,
                        serialName = "Хороший доктор (The Good Doctor)",
                        serialTvShow = "the.good.doctor"
                    )
                ),
                date = "2024-05-18 13:25:40",
                id = 1035580,
                plainText = "Вышел 9-й эпизод 7-го сезона сериала «Хороший доктор (The Good Doctor)» в озвучке «LostFilm»",
                subject = "Хороший доктор. Новый эпизод!",
                text = "Вышел 9-й эпизод 7-го сезона сериала «Хороший доктор» в озвучке «LostFilm».<br><a href='https://sakh.tv/watch/the.good.doctor/07/09/lostfilm/'>Перейти к просмотру.</a>",
                userId = 1563
            ),
            Notification(
                acknowledge = true,
                summary = Summary(
                    action = "new_episode",
                    notificationData = NotificationData(
                        episodeIndex = "01",
                        mediaId = 233176,
                        releaseGroup = "Русский",
                        seasonIndex = "04",
                        serialId = 3370,
                        serialName = "Клинок, рассекающий демонов (Demon Slayer: Kimetsu no Yaiba)",
                        serialTvShow = "kimetsu-no-yaiba"
                    )
                ),
                date = "2024-05-15 15:23:08",
                id = 1031314,
                plainText = "Вышел 1-й эпизод 4-го сезона сериала «Клинок, рассекающий демонов (Demon Slayer: Kimetsu no Yaiba)» в озвучке «Русский»",
                subject = "Клинок, рассекающий демонов. Новый эпизод!",
                text = "Вышел 1-й эпизод 4-го сезона сериала «Клинок, рассекающий демонов» в озвучке «Русский».<br><a href='https://sakh.tv/watch/kimetsu-no-yaiba/04/01/rulang/'>Перейти к просмотру.</a>",
                userId = 1563
            ),
            Notification(
                acknowledge = true,
                summary = Summary(
                    action = "new_episode",
                    notificationData = NotificationData(
                        episodeIndex = "18",
                        mediaId = 233145,
                        releaseGroup = "Русский",
                        seasonIndex = "02",
                        serialId = 4671,
                        serialName = "Реинкарнация безработного (Mushoku Tensei: Jobless Reincarnation)",
                        serialTvShow = "mushoku.tensei"
                    )
                ),
                date = "2024-05-14 00:02:10",
                id = 1030381,
                plainText = "Вышел 18-й эпизод 2-го сезона сериала «Реинкарнация безработного (Mushoku Tensei: Jobless Reincarnation)» в озвучке «Русский»",
                subject = "Реинкарнация безработного. Новый эпизод!",
                text = "Вышел 18-й эпизод 2-го сезона сериала «Реинкарнация безработного» в озвучке «Русский».<br><a href='https://sakh.tv/watch/mushoku.tensei/02/18/rulang/'>Перейти к просмотру.</a>",
                userId = 1563
            ),
            Notification(
                acknowledge = true,
                summary = Summary(
                    action = "new_episode",
                    notificationData = NotificationData(
                        episodeIndex = "08",
                        mediaId = 233042,
                        releaseGroup = "LostFilm",
                        seasonIndex = "07",
                        serialId = 1961,
                        serialName = "Хороший доктор (The Good Doctor)",
                        serialTvShow = "the.good.doctor"
                    )
                ),
                date = "2024-05-12 22:58:56",
                id = 1027429,
                plainText = "Вышел 8-й эпизод 7-го сезона сериала «Хороший доктор (The Good Doctor)» в озвучке «LostFilm»",
                subject = "Хороший доктор. Новый эпизод!",
                text = "Вышел 8-й эпизод 7-го сезона сериала «Хороший доктор» в озвучке «LostFilm».<br><a href='https://sakh.tv/watch/the.good.doctor/07/08/lostfilm/'>Перейти к просмотру.</a>",
                userId = 1563
            ),
            Notification(
                acknowledge = true,
                summary = Summary(
                    action = "new_episode",
                    notificationData = NotificationData(
                        episodeIndex = "17",
                        mediaId = 232763,
                        releaseGroup = "Русский",
                        seasonIndex = "02",
                        serialId = 4671,
                        serialName = "Реинкарнация безработного (Mushoku Tensei: Jobless Reincarnation)",
                        serialTvShow = "mushoku.tensei"
                    )
                ),
                date = "2024-05-07 00:13:18",
                id = 1020165,
                plainText = "Вышел 17-й эпизод 2-го сезона сериала «Реинкарнация безработного (Mushoku Tensei: Jobless Reincarnation)» в озвучке «Русский»",
                subject = "Реинкарнация безработного. Новый эпизод!",
                text = "Вышел 17-й эпизод 2-го сезона сериала «Реинкарнация безработного» в озвучке «Русский».<br><a href='https://sakh.tv/watch/mushoku.tensei/02/17/rulang/'>Перейти к просмотру.</a>",
                userId = 1563
            ),
            Notification(
                acknowledge = true,
                summary = Summary(
                    action = "new_episode",
                    notificationData = NotificationData(
                        episodeIndex = "13",
                        mediaId = 232755,
                        releaseGroup = "RUDUB",
                        seasonIndex = "01",
                        serialId = 5098,
                        serialName = "Ниндзя Камуи (Ninja Kamui)",
                        serialTvShow = "ninja.kamui"
                    )
                ),
                date = "2024-05-06 14:28:01",
                id = 1020022,
                plainText = "Вышел 13-й эпизод 1-го сезона сериала «Ниндзя Камуи (Ninja Kamui)» в озвучке «RUDUB»",
                subject = "Ниндзя Камуи. Новый эпизод!",
                text = "Вышел 13-й эпизод 1-го сезона сериала «Ниндзя Камуи» в озвучке «RUDUB».<br><a href='https://sakh.tv/watch/ninja.kamui/01/13/rudub/'>Перейти к просмотру.</a>",
                userId = 1563
            )
        )
    )

    fun getNotificationsList() = notificationsList
}