package com.example.sakhcast.data.samples

import com.example.sakhcast.model.CoverColors
import com.example.sakhcast.model.MovieCard
import com.example.sakhcast.model.User

object MovieCardSample {
    val movieCard = MovieCard(
        adult = false,
        ageLimits = 18,
        available = true,
        cover = "https://cf1-static-eu1.stvcdn.org/img/posters/c9/ik/jqxsjy3dn3-250x366.png",
        coverAlt = "https://cf4-static-eu1.stvcdn.org/img/cache/NW/Zj/ZDdlYTllYmU0YTFkNjppbWcvcG9zdGVycy9jOS9pay9qcXhzankzZG4zLnBuZzoyNTA6MzY2OjA6OTA.",
        coverColors = CoverColors(
            background1 = "#efd9bc",
            background2 = "#c8b396",
            lum = 84.70297029702971,
            text = "#150000",
            title = "#77554b"
        ),
        coverH = 366,
        coverLq = "https://cf4-static-eu1.stvcdn.org/img/posters/c9/ik/jqxsjy3dn3-25x36.png",
        coverW = 250,
        id = 7425,
        idAlpha = "dog_eat_dog-2016",
        imdb = true,
        imdbRating = 4.8,
        kp = true,
        kpRating = 4.916,
        link = "/movie/dog_eat_dog-2016/",
        originTitle = "Dog Eat Dog",
        progress = null,
        releaseDate = "2016-11-04",
        ruTitle = "Человек человеку волк",
        runtime = 93,
        user = User(
            favKind = null,
            isFav = false
        )
    )
}