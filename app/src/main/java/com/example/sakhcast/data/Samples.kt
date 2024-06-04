package com.example.sakhcast.data

import com.example.sakhcast.model.MovieCard
import com.example.sakhcast.model.Notification
import com.example.sakhcast.model.SeriesCard
import com.example.sakhcast.model.UserData

object Samples {
    val seriesCardList: List<SeriesCard> = listOf(
        SeriesCard(
            id = 1,
            name = "Сериал 1",
            imdbRating = 9.0,
            kinopoiskRating = 9.0,
            releaseYear = 2022,
            totalSeasonsAndSeries = "1 Сезон 23 Cерии"
        ),
        SeriesCard(
            id = 2,
            name = "Сериал 2",
            imdbRating = 8.5,
            kinopoiskRating = 9.0,
            releaseYear = 2023,
            totalSeasonsAndSeries = "3 Сезон 15 Cерии"
        ),
        SeriesCard(
            id = 3,
            name = "Сериал 3",
            imdbRating = 7.8,
            kinopoiskRating = 9.5,
            releaseYear = 2021,
            totalSeasonsAndSeries = "2 Сезон 20 Cерии"
        ),
        SeriesCard(
            id = 4,
            name = "Сериал 4",
            imdbRating = 8.9,
            kinopoiskRating = 9.8,
            releaseYear = 2020,
            totalSeasonsAndSeries = "5 Сезон 28 Cерии"
        ),
        SeriesCard(
            id = 5,
            name = "Сериал 5",
            imdbRating = 7.2,
            kinopoiskRating = 8.2,
            releaseYear = 2019,
            totalSeasonsAndSeries = "1 Сезон 10 Cерии"
        ),
        SeriesCard(
            id = 6,
            name = "Сериал 6",
            imdbRating = 8.3,
            kinopoiskRating = 7.7,
            releaseYear = 2018,
            totalSeasonsAndSeries = "4 Сезон 24 Cерии"
        ),
        SeriesCard(
            id = 7,
            name = "Сериал 7",
            imdbRating = 9.1,
            kinopoiskRating = 6.5,
            releaseYear = 2022,
            totalSeasonsAndSeries = "2 Сезон 18 Cерии"
        ),
        SeriesCard(
            id = 8,
            name = "Сериал 8",
            imdbRating = 7.5,
            kinopoiskRating = 7.3,
            releaseYear = 2023,
            totalSeasonsAndSeries = "3 Сезон 22 Cерии"
        ),
        SeriesCard(
            id = 9,
            name = "Сериал 9",
            imdbRating = 8.7,
            kinopoiskRating = 8.0,
            releaseYear = 2021,
            totalSeasonsAndSeries = "2 Сезон 15 Cерии"
        ),
        SeriesCard(
            id = 10,
            name = "Сериал 10",
            imdbRating = 8.0,
            kinopoiskRating = 9.8,
            releaseYear = 2020,
            totalSeasonsAndSeries = "4 Сезон 20 Cерии"
        )
    )
    val movieCardLists: List<MovieCard> = listOf(
        MovieCard(
            id = 1,
            ruTitle = "Фильм 1",
            imdbRating = 9.0,
            kinopoiskRating = 6.0,
            releaseDate = "2022",
            duration = "1ч 30 мин"
        ),
        MovieCard(
            id = 2,
            ruTitle = "Фильм 2",
            imdbRating = 8.5,
            kinopoiskRating = 7.0,
            releaseDate = "2023",
            duration = "2ч 15 мин"
        ),
        MovieCard(
            id = 3,
            ruTitle = "Фильм 3",
            imdbRating = 7.8,
            kinopoiskRating = 8.5,
            releaseDate = "2021",
            duration = "1ч 50 мин"
        ),
        MovieCard(
            id = 4,
            ruTitle = "Фильм 4",
            imdbRating = 8.9,
            kinopoiskRating = 9.8,
            releaseDate = "2020",
            duration = "2ч 5 мин"
        ),
        MovieCard(
            id = 5,
            ruTitle = "Фильм 5",
            imdbRating = 7.2,
            kinopoiskRating = 6.2,
            releaseDate = "2019",
            duration = "1ч 40 мин"
        ),
        MovieCard(
            id = 6,
            ruTitle = "Фильм 6",
            imdbRating = 8.3,
            kinopoiskRating = 6.7,
            releaseDate = "2018",
            duration = "2ч 20 мин"
        ),
        MovieCard(
            id = 7,
            ruTitle = "Фильм 7",
            imdbRating = 9.1,
            kinopoiskRating = 9.5,
            releaseDate = "2022",
            duration = "2ч 10 мин"
        ),
        MovieCard(
            id = 8,
            ruTitle = "Фильм 8",
            imdbRating = 7.5,
            kinopoiskRating = 6.3,
            releaseDate = "2023",
            duration = "1ч 55 мин"
        ),
        MovieCard(
            id = 9,
            ruTitle = "Фильм 9",
            imdbRating = 8.7,
            kinopoiskRating = 7.0,
            releaseDate = "2021",
            duration = "2ч 30 мин"
        ),
        MovieCard(
            id = 10,
            ruTitle = "Фильм 10",
            imdbRating = 8.0,
            kinopoiskRating = 7.8,
            releaseDate = "2020",
            duration = "2ч 25 мин"
        )
    )
    val notificationList = listOf<Notification>(
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
        Notification(text = "Вышел 3-й эпизод 1-го сезона сериала \"Городок\".<br><a href='https://sakh.tv/watch/the.town/01/03/viruse_project/'>Перейти к просмотру.</a>"),
    )
    private val user = UserData(userLogin = "1", userPassword = "1")

    fun isLogin(loginInput: String, passwordInput: String) =
        (user.userLogin == loginInput && user.userPassword == passwordInput)

    fun getAllNotifications(): List<Notification> = notificationList

    fun getAllSeries(): List<SeriesCard> = seriesCardList
    fun getAllMovies(): List<MovieCard> = movieCardLists

    fun getOneMovie(): MovieCard = movieCardLists[1]
    fun getOneSeries(): SeriesCard = seriesCardList[1]
}
