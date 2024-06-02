package com.example.sakhcast.data

import com.example.sakhcast.model.AudioTrack
import com.example.sakhcast.model.BackdropColors
import com.example.sakhcast.model.Cast
import com.example.sakhcast.model.Download
import com.example.sakhcast.model.File
import com.example.sakhcast.model.Genre
import com.example.sakhcast.model.Language
import com.example.sakhcast.model.Movie
import com.example.sakhcast.model.Pd
import com.example.sakhcast.model.Person
import com.example.sakhcast.model.PosterColors
import com.example.sakhcast.model.ProductionCompany
import com.example.sakhcast.model.ProductionCountry
import com.example.sakhcast.model.Sources
import com.example.sakhcast.model.Subtitle
import com.example.sakhcast.model.Track
import com.example.sakhcast.model.User
import com.example.sakhcast.model.Variant

object MovieSample {
    val movie = Movie(
        ad = false,
        adult = false,
        agelimits = 18,
        audioTracks = listOf(
            AudioTrack(id = 1123, iso6391 = "rus", title = "Russian Dub (Пифагор)"),
            AudioTrack(id = 1124, iso6391 = "rus", title = "Russian (Jaskier)"),
            AudioTrack(id = 1125, iso6391 = "rus", title = "Russian (HDRezka Studio)"),
            AudioTrack(id = 1126, iso6391 = "rus", title = "Russian (Визгунов)"),
            AudioTrack(id = 1127, iso6391 = "rus", title = "Russian (Есарев)"),
            AudioTrack(id = 1128, iso6391 = "rus", title = "Russian (Назаров)"),
            AudioTrack(id = 1129, iso6391 = "rus", title = "Russian (feofanio)"),
            AudioTrack(id = 1130, iso6391 = "eng", title = "English")
        ),
        backdrop = "https://cf2-static-eu1.stvcdn.org/img/backdrops/ru/ys/odkm6ral-960x540.png",
        backdropAlt = "https://cf1-static-eu1.stvcdn.org/img/cache/Mj/k2/ZjVkM2JhMTY0NWVjMTppbWcvYmFja2Ryb3BzL3J1L3lzL29ka202cmFsLnBuZzo5NjA6NTQwOjA6OTA.",
        backdropColors = BackdropColors(
            background1 = "#8bcccd",
            background2 = "#64a5a6",
            lum = 144.41419141914,
            text = "#f3ffff",
            title = "#0a1616"
        ),
        budget = 190000000,
        createDate = "2023-08-30",
        downloads = listOf(
            Download(
                id = 307,
                size = "1.50ГБ",
                sizeRaw = 1607191512,
                w = 846,
                h = 360,
                ql = "360p",
                src = "https://ext-nas1-eu1.stvcdn.org/v/b01/the_matrix_resurrections-2021/the_matrix_resurrections-2021.360p.mp4?r=10000k&uid=5297&dl=true&st=eMJ95iyJ6Dc2swEmV5pfkA&e=1716558887",

            ),
            Download(
                id = 308,
                size = "1.91ГБ",
                sizeRaw = 2047864294,
                w = 1128,
                h = 480,
                ql = "SD",
                src = "https://ext-nas1-eu1.stvcdn.org/v/b01/the_matrix_resurrections-2021/the_matrix_resurrections-2021.480p.mp4?r=10000k&uid=5297&dl=true&st=maKQIU-ecZUr86VxvRRTcQ&e=1716558887",

            ),
            Download(
                id = 309,
                size = "3.16ГБ",
                sizeRaw = 3388218039,
                w = 1694,
                h = 720,
                ql = "HD",
                src = "https://ext-nas1-eu1.stvcdn.org/v/b01/the_matrix_resurrections-2021/the_matrix_resurrections-2021.720p.mp4?r=10000k&uid=5297&dl=true&st=R3V09S1Wgis5mZQwWlKKbw&e=1716558887",

            ),
            Download(
                id = 310,
                size = "8.54ГБ",
                sizeRaw = 9165778240,
                w = 1920,
                h = 816,
                ql = "FullHD",
                src = "https://ext-nas1-eu1.stvcdn.org/v/b01/the_matrix_resurrections-2021/the_matrix_resurrections-2021.1080p.mp4?r=10000k&uid=5297&dl=true&st=Y0PKxCA8c6bjpxDnapZ1Jg&e=1716558887",

            )
        ),
        favorites = 36,
        genres = listOf(
            Genre(id = 1, name = "приключения", variant = "none"),
            Genre(id = 3, name = "фантастика", variant = "none"),
            Genre(id = 6, name = "боевик", variant = "none")
        ),
        hidden = false,
        id = 84,
        idAlpha = "the_matrix_resurrections-2021",
        imdbId = "tt10838180",
        imdbRating = 5.7,
        isDarkBackdrop = false,
        kpId = 1294123,
        kpRating = 5.727,
        languages = listOf(
            Language(id = 38, name = "английский")
        ),
        originLang = "английский",
        originTitle = "The Matrix Resurrections",
        overview = "Продолжение истории, рассказанной в первом фильме \"МАТРИЦА\", воссоединяющее кинематографических картин Нео и Тринити, когда они отправляются обратно в Матрицу и еще глубже в кроличью нору. Новое захватывающее приключение с действием и эпическим масштабом, действие которого разворачивается в знакомом, но еще более провокационном мире, где реальность более субъективна, чем когда-либо, и все, что требуется, чтобы увидеть правду, - это освободить свой разум.",
        popularity = 183.885,
        poster = "https://cf2-static-eu1.stvcdn.org/img/posters/0i/7v/7k55siqw-396x594.png",
        posterAlt = "https://cf3-static-eu1.stvcdn.org/img/cache/YW/I3/YzQyYmMxYzAyZmY5MDppbWcvcG9zdGVycy8waS83di83azU1c2lxdy5wbmc6Mzk2OjU5NDowOjkw.",
        posterColors = PosterColors(
            background1 = "#edfff7",
            background2 = "#c7d8d1",
            lum = 121.36798679868,
            text = "#000809",
            title = "#0d1212"
        ),
        productionCompanies = listOf(
            ProductionCompany(
                id = 44,
                name = "Warner Bros. Pictures",
                originCountry = "США",
                logo = "https://cf1-static-eu1.stvcdn.org/img/cache/N2/Nj/YjZiOTExYzMwMjJkYjppbWcvbG9nb3Mvci9zdGs4dHBlcC5wbmc6NjQ6NjQ6Mjo5MA.png",
                logoAlt = "https://cf1-static-eu1.stvcdn.org/img/cache/N2/Nj/YjZiOTExYzMwMjJkYjppbWcvbG9nb3Mvci9zdGs4dHBlcC5wbmc6NjQ6NjQ6Mjo5MA."
            ),
            ProductionCompany(
                id = 173,
                name = "Village Roadshow Pictures",
                originCountry = "США",
                logo = "https://cf4-static-eu1.stvcdn.org/img/cache/Nj/g3/MzhiZmIwYWY4NzMzMTppbWcvbG9nb3MvaC9lMHZseGxiNi5wbmc6NjQ6NjQ6Mjo5MA.png",
                logoAlt = "https://cf2-static-eu1.stvcdn.org/img/cache/Nj/g3/MzhiZmIwYWY4NzMzMTppbWcvbG9nb3MvaC9lMHZseGxiNi5wbmc6NjQ6NjQ6Mjo5MA."
            ),
            ProductionCompany(
                id = 174,
                name = "Venus Castina Productions",
                originCountry = "США",
                logo = "null",
                logoAlt = "null"
            )
        ),
        productionCountries = listOf(
            ProductionCountry(id = 4, name = "США")
        ),
        releaseDate = "2021-12-16",
        revenue = 156497322,
        ruTitle = "Матрица: Воскрешение",
        runtime = 148,
        sources = Sources(
            defaultSource = "https://ext-origin-nas1-eu1.stvcdn.org/hlsmov01/-84/4/1000000000/master.m3u8?uid=5297&st=mETe8ye-xUKDiOuvSpwNfA&e=1716558887",
            pd = Pd(
                files = listOf(
                    File(
                        id = 307,
                        w = 846,
                        h = 360,
                        ql = "360p",
                        src = "https://ext-nas1-eu1.stvcdn.org/v/b01/the_matrix_resurrections-2021/the_matrix_resurrections-2021.360p.mp4?r=531k&uid=5297&st=3-srg3_7B1uFNRG4_i9fBw&e=1716558887"
                    ),
                    File(
                        id = 308,
                        w = 1128,
                        h = 480,
                        ql = "SD",
                        src = "https://ext-nas1-eu1.stvcdn.org/v/b01/the_matrix_resurrections-2021/the_matrix_resurrections-2021.480p.mp4?r=676k&uid=5297&st=U3aHL-cmQAFhWSgdmTwtDg&e=1716558887"
                    ),
                    File(
                        id = 309,
                        w = 1694,
                        h = 720,
                        ql = "HD",
                        src = "https://ext-nas1-eu1.stvcdn.org/v/b01/the_matrix_resurrections-2021/the_matrix_resurrections-2021.720p.mp4?r=1118k&uid=5297&st=0JCYI9PCYXCW2Xk7LBWLgQ&e=1716558887"
                    ),
                    File(
                        id = 310,
                        w = 1920,
                        h = 816,
                        ql = "FullHD",
                        src = "https://ext-nas1-eu1.stvcdn.org/v/b01/the_matrix_resurrections-2021/the_matrix_resurrections-2021.1080p.mp4?r=3026k&uid=5297&st=grLL0129B4sBw9f6zXvO2g&e=1716558887"
                    )
                ),
                tracks = listOf(
                    Track(
                        id = 1668,
                        label = "1. Russian (FOCS) [rus]",
                        language = "rus",
                        src = "https://cf1-static-eu1.stvcdn.org/tracks/movies/84/the_matrix_resurrections-2021.9.vtt"
                    ),
                    Track(
                        id = 1669,
                        label = "2. Russian (kornickolay) [rus]",
                        language = "rus",
                        src = "https://cf1-static-eu1.stvcdn.org/tracks/movies/84/the_matrix_resurrections-2021.10.vtt"
                    ),
                    Track(
                        id = 1670,
                        label = "3. Russian (SDH) [rus]",
                        language = "rus",
                        src = "https://cf4-static-eu1.stvcdn.org/tracks/movies/84/the_matrix_resurrections-2021.11.vtt"
                    ),
                    Track(
                        id = 1671,
                        label = "4. Форсированные [rus]",
                        language = "rus",
                        src = "https://cf3-static-eu1.stvcdn.org/tracks/movies/84/the_matrix_resurrections-2021.12.vtt"
                    ),
                    Track(
                        id = 1672,
                        label = "5. Russian (feofanio) [rus]",
                        language = "rus",
                        src = "https://cf4-static-eu1.stvcdn.org/tracks/movies/84/the_matrix_resurrections-2021.13.vtt"
                    ),
                    Track(
                        id = 1673,
                        label = "6. English [eng]",
                        language = "eng",
                        src = "https://cf4-static-eu1.stvcdn.org/tracks/movies/84/the_matrix_resurrections-2021.14.vtt"
                    )
                )
            ),
            variants = listOf(
                Variant(
                    type = "hlsmov01",
                    url = "https://ext-origin-nas1-eu1.stvcdn.org/hlsmov01/-84/4/1000000000/master.m3u8?uid=5297&st=mETe8ye-xUKDiOuvSpwNfA&e=1716558887"
                ),
                Variant(
                    type = "hls",
                    url = "https://ext-origin-nas1-eu1.stvcdn.org/hls/-84/4/1000000000/master.m3u8?uid=5297&st=QtQlc8MfIpdJTRGch3EGWw&e=1716558887"
                ),
                Variant(
                    type = "hls2",
                    url = "https://ext-origin-nas1-eu1.stvcdn.org/hls2/-84/4/1000000000/master.m3u8?uid=5297&st=9fMDOzBxsOyV-V9YTTEkyA&e=1716558887"
                )
            )
        ),
        status = "выпущен",
        subtitles = listOf(
            Subtitle(id = 1668, iso6391 = "rus", title = "Russian (FOCS) [rus]"),
            Subtitle(id = 1669, iso6391 = "rus", title = "Russian (kornickolay) [rus]"),
            Subtitle(id = 1670, iso6391 = "rus", title = "Russian (SDH) [rus]"),
            Subtitle(id = 1671, iso6391 = "rus", title = "Форсированные [rus]"),
            Subtitle(id = 1672, iso6391 = "rus", title = "Russian (feofanio) [rus]"),
            Subtitle(id = 1673, iso6391 = "eng", title = "English [eng]")
        ),
        tagline = "«Выбор за тобой»",
        url = "/movie/the_matrix_resurrections-2021/",
        user = User(favKind = "", isFav = false, position = 5319),
        views = 142,
        cast = Cast(
            voiceActor = listOf(
                Person(
                    id = 65,
                    kpId = 1036893,
                    ruName = "Геннадий Новиков",
                    enName = null,
                    photo = "https://cf2-static-eu1.stvcdn.org/img/cache/MW/Iz/MGM2NTZkZjNmNmY2NzppbWcvcGVyc29ucy9zYy9rMC8zZmphZWNqMy5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf2-static-eu1.stvcdn.org/img/cache/MW/Iz/MGM2NTZkZjNmNmY2NzppbWcvcGVyc29ucy9zYy9rMC8zZmphZWNqMy5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "актеры дубляжа",
                    enRole = "voice_actor"
                ),
                Person(
                    id = 385,
                    kpId = 1616407,
                    ruName = "Всеволод Кузнецов",
                    enName = null,
                    photo = "https://cf3-static-eu1.stvcdn.org/img/cache/ZD/M2/ZmJlMDdiYmZjZjM2MDppbWcvcGVyc29ucy85YS9iYy9yejNsYTFuYS5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf2-static-eu1.stvcdn.org/img/cache/ZD/M2/ZmJlMDdiYmZjZjM2MDppbWcvcGVyc29ucy85YS9iYy9yejNsYTFuYS5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "актеры дубляжа",
                    enRole = "voice_actor"
                ),
                Person(
                    id = 862,
                    kpId = 1608429,
                    ruName = "Александр Носков",
                    enName = null,
                    photo = "https://cf4-static-eu1.stvcdn.org/img/cache/Yj/Ew/MTM4M2Y5MTY5MGY2NTppbWcvcGVyc29ucy9qMC9nOC9jaWhsYmNlZS5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf4-static-eu1.stvcdn.org/img/cache/Yj/Ew/MTM4M2Y5MTY5MGY2NTppbWcvcGVyc29ucy9qMC9nOC9jaWhsYmNlZS5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "актеры дубляжа",
                    enRole = "voice_actor"
                ),
                Person(
                    id = 1818,
                    kpId = 1654400,
                    ruName = "Елена Соловьева",
                    enName = null,
                    photo = "https://cf3-static-eu1.stvcdn.org/img/cache/Y2/Jl/OTA1OTVmNzA5MGFhMTppbWcvcGVyc29ucy9jMi9pNy91dnp0cHh1MC5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf1-static-eu1.stvcdn.org/img/cache/Y2/Jl/OTA1OTVmNzA5MGFhMTppbWcvcGVyc29ucy9jMi9pNy91dnp0cHh1MC5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "актеры дубляжа",
                    enRole = "voice_actor"
                ),
                Person(
                    id = 1819,
                    kpId = 3704185,
                    ruName = "Анна Киселёва",
                    enName = null,
                    photo = "https://cf1-static-eu1.stvcdn.org/img/cache/ZT/Yx/NDgwNjNmYWQzNDBiMzppbWcvcGVyc29ucy9peC8zcy9jb3E5ZmZuYS5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf2-static-eu1.stvcdn.org/img/cache/ZT/Yx/NDgwNjNmYWQzNDBiMzppbWcvcGVyc29ucy9peC8zcy9jb3E5ZmZuYS5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "актеры дубляжа",
                    enRole = "voice_actor"
                )
            ),
            designer = listOf(
                Person(
                    id = 132,
                    kpId = 3690022,
                    ruName = "Рави Бансал",
                    enName = "Ravi Bansal",
                    photo = "https://cf4-static-eu1.stvcdn.org/img/cache/NT/Uw/YjIwNTU1NmRlZGJiYjppbWcvcGVyc29ucy84aS9xci84dDk1b3FjaS5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf2-static-eu1.stvcdn.org/img/cache/NT/Uw/YjIwNTU1NmRlZGJiYjppbWcvcGVyc29ucy84aS9xci84dDk1b3FjaS5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "художники",
                    enRole = "designer"
                ),
                Person(
                    id = 1034,
                    kpId = 1988678,
                    ruName = "Барбара Манч",
                    enName = "Barbara Munch",
                    photo = "https://cf4-static-eu1.stvcdn.org/img/cache/Nj/Zl/MmZlYTY5MTVjNjEzNzppbWcvcGVyc29ucy8zYi9mdC9vbGlzanlxeS5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf3-static-eu1.stvcdn.org/img/cache/Nj/Zl/MmZlYTY5MTVjNjEzNzppbWcvcGVyc29ucy8zYi9mdC9vbGlzanlxeS5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "художники",
                    enRole = "designer"
                ),
                Person(
                    id = 1380,
                    kpId = 1986467,
                    ruName = "Майя Симогути",
                    enName = "Maya Shimoguchi",
                    photo = "https://cf4-static-eu1.stvcdn.org/img/cache/OW/Rl/MDdmMDA1ODFiMGIxODppbWcvcGVyc29ucy8wNC9haS92a3VtMmZmZS5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf1-static-eu1.stvcdn.org/img/cache/OW/Rl/MDdmMDA1ODFiMGIxODppbWcvcGVyc29ucy8wNC9haS92a3VtMmZmZS5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "художники",
                    enRole = "designer"
                ),
                Person(
                    id = 1801,
                    kpId = 1619589,
                    ruName = "Ричард Блум",
                    enName = "Richard Bloom",
                    photo = "https://cf4-static-eu1.stvcdn.org/img/cache/MW/Yy/MjI4YjNhNmZkMzVkMjppbWcvcGVyc29ucy9uZi9yOC9laGdiN2x2ci5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf4-static-eu1.stvcdn.org/img/cache/MW/Yy/MjI4YjNhNmZkMzVkMjppbWcvcGVyc29ucy9uZi9yOC9laGdiN2x2ci5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "художники",
                    enRole = "designer"
                ),
                Person(
                    id = 1802,
                    kpId = 3553971,
                    ruName = "Дэниэл Фрэнк",
                    enName = "Daniel Frank",
                    photo = "https://cf4-static-eu1.stvcdn.org/img/cache/Mm/Fi/NDQ5ZGU5ZmFmNjgzZTppbWcvcGVyc29ucy83ay91ZS82bXR4c3ZtZy5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf4-static-eu1.stvcdn.org/img/cache/Mm/Fi/NDQ5ZGU5ZmFmNjgzZTppbWcvcGVyc29ucy83ay91ZS82bXR4c3ZtZy5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "художники",
                    enRole = "designer"
                ),
                Person(
                    id = 1803,
                    kpId = 1988467,
                    ruName = "Штефан О. Гесслер",
                    enName = "Stephan O. Gessler",
                    photo = "https://cf3-static-eu1.stvcdn.org/img/cache/Yj/Rk/Y2ExM2YxMjYxZmNmNTppbWcvcGVyc29ucy8xOS80cC9lOGpzZTBxaS5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf3-static-eu1.stvcdn.org/img/cache/Yj/Rk/Y2ExM2YxMjYxZmNmNTppbWcvcGVyc29ucy8xOS80cC9lOGpzZTBxaS5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "художники",
                    enRole = "designer"
                ),
                Person(
                    id = 1804,
                    kpId = 2961007,
                    ruName = "Вольфганг Мечан",
                    enName = "Wolfgang Metschan",
                    photo = "https://cf3-static-eu1.stvcdn.org/img/cache/Zm/Nh/ZDRmOTY0OWY3NTgxMTppbWcvcGVyc29ucy84dy9rNS84NW42ZjhhYi5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf2-static-eu1.stvcdn.org/img/cache/Zm/Nh/ZDRmOTY0OWY3NTgxMTppbWcvcGVyc29ucy84dy9rNS84NW42ZjhhYi5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "художники",
                    enRole = "designer"
                ),
                Person(
                    id = 1805,
                    kpId = 1988862,
                    ruName = "Аня Мюллер",
                    enName = "Anja Müller",
                    photo = "https://cf1-static-eu1.stvcdn.org/img/cache/MT/g2/MTNlMWE1MjFjMGIxOTppbWcvcGVyc29ucy9rdS96My9nYmE3YTRtZi5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf2-static-eu1.stvcdn.org/img/cache/MT/g2/MTNlMWE1MjFjMGIxOTppbWcvcGVyc29ucy9rdS96My9nYmE3YTRtZi5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "художники",
                    enRole = "designer"
                ),
                Person(
                    id = 1806,
                    kpId = 2040351,
                    ruName = "Тарния Николь",
                    enName = "Tarnia Nicol",
                    photo = "https://cf2-static-eu1.stvcdn.org/img/cache/M2/Q5/ZjJhMDA2NDgxZDIxMDppbWcvcGVyc29ucy8zdy94OC9tZGhrMHJneS5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf4-static-eu1.stvcdn.org/img/cache/M2/Q5/ZjJhMDA2NDgxZDIxMDppbWcvcGVyc29ucy8zdy94OC9tZGhrMHJneS5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "художники",
                    enRole = "designer"
                ),
                Person(
                    id = 1807,
                    kpId = 2003769,
                    ruName = "Нэнси Ноблетт",
                    enName = "Nanci Noblett",
                    photo = "https://cf3-static-eu1.stvcdn.org/img/cache/ZG/U1/MmYxOTk0ZWE2YTc3YzppbWcvcGVyc29ucy9tdS91cy9zdnUzZmt3bC5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf4-static-eu1.stvcdn.org/img/cache/ZG/U1/MmYxOTk0ZWE2YTc3YzppbWcvcGVyc29ucy9tdS91cy9zdnUzZmt3bC5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "художники",
                    enRole = "designer"
                ),
                Person(
                    id = 1808,
                    kpId = 2187704,
                    ruName = "Эстер Шрайнер",
                    enName = "Esther Schreiner",
                    photo = "https://cf3-static-eu1.stvcdn.org/img/cache/ZD/A3/YzA0NTRjZmUyNjc4NzppbWcvcGVyc29ucy9sdy9qbS9yd3JraWZqdi5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf4-static-eu1.stvcdn.org/img/cache/ZD/A3/YzA0NTRjZmUyNjc4NzppbWcvcGVyc29ucy9sdy9qbS9yd3JraWZqdi5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "художники",
                    enRole = "designer"
                ),
                Person(
                    id = 1809,
                    kpId = 1816147,
                    ruName = "Линдсэй Паг",
                    enName = "Lindsay Pugh",
                    photo = "https://cf1-static-eu1.stvcdn.org/img/cache/MD/c2/MzM1OWE5ZjdjZDQ0MzppbWcvcGVyc29ucy9pcS82OS83Y3hreGkzZy5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf2-static-eu1.stvcdn.org/img/cache/MD/c2/MzM1OWE5ZjdjZDQ0MzppbWcvcGVyc29ucy9pcS82OS83Y3hreGkzZy5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "художники",
                    enRole = "designer"
                ),
                Person(
                    id = 1810,
                    kpId = 2009105,
                    ruName = "Лиза Бреннан",
                    enName = "Lisa Brennan",
                    photo = "https://cf4-static-eu1.stvcdn.org/img/cache/NW/Qy/MGU0Mzg3MWRmZTIwODppbWcvcGVyc29ucy80by85YS95d21haXZtMC5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf3-static-eu1.stvcdn.org/img/cache/NW/Qy/MGU0Mzg3MWRmZTIwODppbWcvcGVyc29ucy80by85YS95d21haXZtMC5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "художники",
                    enRole = "designer"
                )
            ),
            actor = listOf(
                Person(
                    id = 162,
                    kpId = 7836,
                    ruName = "Киану Ривз",
                    enName = "Keanu Reeves",
                    photo = "https://cf2-static-eu1.stvcdn.org/img/cache/Mm/Zl/ODgwYTZlZjI1OGJkZDppbWcvcGVyc29ucy9rZi80bC92emUzZjgzbi5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf1-static-eu1.stvcdn.org/img/cache/Mm/Zl/ODgwYTZlZjI1OGJkZDppbWcvcGVyc29ucy9rZi80bC92emUzZjgzbi5wbmc6MTI4OjEyODowOjkw.",
                    description = "Neo / Thomas Anderson",
                    ruRole = "актеры",
                    enRole = "actor"
                ),
                Person(
                    id = 390,
                    kpId = 1452349,
                    ruName = "Джонатан Грофф",
                    enName = "Jonathan Groff",
                    photo = "https://cf2-static-eu1.stvcdn.org/img/cache/ZT/Iz/ZWRhNWFhOTY4ZmM3MTppbWcvcGVyc29ucy82MC90ei9ncXd5bTdrZS5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf4-static-eu1.stvcdn.org/img/cache/ZT/Iz/ZWRhNWFhOTY4ZmM3MTppbWcvcGVyc29ucy82MC90ei9ncXd5bTdrZS5wbmc6MTI4OjEyODowOjkw.",
                    description = "Smith",
                    ruRole = "актеры",
                    enRole = "actor"
                ),
                Person(
                    id = 912,
                    kpId = 4296336,
                    ruName = "Яхья Абдул-Матин II",
                    enName = "Yahya Abdul-Mateen II",
                    photo = "https://cf1-static-eu1.stvcdn.org/img/cache/Nz/Y2/OTg3NzZiODcxZmYwYjppbWcvcGVyc29ucy9sOS90MC8ybmcxcmNubC5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf3-static-eu1.stvcdn.org/img/cache/Nz/Y2/OTg3NzZiODcxZmYwYjppbWcvcGVyc29ucy9sOS90MC8ybmcxcmNubC5wbmc6MTI4OjEyODowOjkw.",
                    description = "Morpheus / Agent Smith",
                    ruRole = "актеры",
                    enRole = "actor"
                ),
                Person(
                    id = 1160,
                    kpId = 520,
                    ruName = "Джада Пинкетт Смит",
                    enName = "Jada Pinkett Smith",
                    photo = "https://cf2-static-eu1.stvcdn.org/img/cache/Nz/Q3/YTNjMGUzNzQ4ZmI2NDppbWcvcGVyc29ucy9wcC9teC83aTB6d2xyYy5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf1-static-eu1.stvcdn.org/img/cache/Nz/Q3/YTNjMGUzNzQ4ZmI2NDppbWcvcGVyc29ucy9wcC9teC83aTB6d2xyYy5wbmc6MTI4OjEyODowOjkw.",
                    description = "Niobe",
                    ruRole = "актеры",
                    enRole = "actor"
                ),
                Person(
                    id = 1412,
                    kpId = 2006922,
                    ruName = "Джессика Хенвик",
                    enName = "Jessica Henwick",
                    photo = "https://cf2-static-eu1.stvcdn.org/img/cache/MG/Ji/Yjk5MzUwM2FhZmI3ZTppbWcvcGVyc29ucy8xcC9maS84dXl6ZGo1eC5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf1-static-eu1.stvcdn.org/img/cache/MG/Ji/Yjk5MzUwM2FhZmI3ZTppbWcvcGVyc29ucy8xcC9maS84dXl6ZGo1eC5wbmc6MTI4OjEyODowOjkw.",
                    description = "Bugs",
                    ruRole = "актеры",
                    enRole = "actor"
                ),
                Person(
                    id = 1794,
                    kpId = 6226,
                    ruName = "Кэрри-Энн Мосс",
                    enName = "Carrie-Anne Moss",
                    photo = "https://cf2-static-eu1.stvcdn.org/img/cache/ZW/Qx/ODRkYWY5N2FlYzRjMzppbWcvcGVyc29ucy9qeC8zdS9zemMxZnlpei5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf2-static-eu1.stvcdn.org/img/cache/ZW/Qx/ODRkYWY5N2FlYzRjMzppbWcvcGVyc29ucy9qeC8zdS9zemMxZnlpei5wbmc6MTI4OjEyODowOjkw.",
                    description = "Trinity / Tiffany",
                    ruRole = "актеры",
                    enRole = "actor"
                ),
                Person(
                    id = 1795,
                    kpId = 36870,
                    ruName = "Нил Патрик Харрис",
                    enName = "Neil Patrick Harris",
                    photo = "https://cf2-static-eu1.stvcdn.org/img/cache/Nj/Yy/YzEwMTVmMmVhYjZhMzppbWcvcGVyc29ucy9uMS9kci9icW50cnowdi5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf2-static-eu1.stvcdn.org/img/cache/Nj/Yy/YzEwMTVmMmVhYjZhMzppbWcvcGVyc29ucy9uMS9kci9icW50cnowdi5wbmc6MTI4OjEyODowOjkw.",
                    description = "The Analyst",
                    ruRole = "актеры",
                    enRole = "actor"
                ),
                Person(
                    id = 1796,
                    kpId = 229163,
                    ruName = "Приянка Чопра Джонас",
                    enName = "Priyanka Chopra Jonas",
                    photo = "https://cf4-static-eu1.stvcdn.org/img/cache/Mz/U4/Y2NmZTM2NDQ3N2U3ZDppbWcvcGVyc29ucy9xbC82ZC94ODk3dzNyZi5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf2-static-eu1.stvcdn.org/img/cache/Mz/U4/Y2NmZTM2NDQ3N2U3ZDppbWcvcGVyc29ucy9xbC82ZC94ODk3dzNyZi5wbmc6MTI4OjEyODowOjkw.",
                    description = "Sati",
                    ruRole = "актеры",
                    enRole = "actor"
                ),
                Person(
                    id = 1797,
                    kpId = 3843,
                    ruName = "Кристина Риччи",
                    enName = "Christina Ricci",
                    photo = "https://cf2-static-eu1.stvcdn.org/img/cache/Nm/Iy/ZWEwYzI3ZmY2ZDZiZDppbWcvcGVyc29ucy90dS82eS9pZm93NnNkOC5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf2-static-eu1.stvcdn.org/img/cache/Nm/Iy/ZWEwYzI3ZmY2ZDZiZDppbWcvcGVyc29ucy90dS82eS9pZm93NnNkOC5wbmc6MTI4OjEyODowOjkw.",
                    description = "Gwyn de Vere",
                    ruRole = "актеры",
                    enRole = "actor"
                ),
                Person(
                    id = 1798,
                    kpId = 22343,
                    ruName = "Ламбер Вильсон",
                    enName = "Lambert Wilson",
                    photo = "https://cf4-static-eu1.stvcdn.org/img/cache/Zm/Qz/NTBhZWZlN2UzMzJmNTppbWcvcGVyc29ucy85ci9ycS9naWJ3dTgwaS5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf1-static-eu1.stvcdn.org/img/cache/Zm/Qz/NTBhZWZlN2UzMzJmNTppbWcvcGVyc29ucy85ci9ycS9naWJ3dTgwaS5wbmc6MTI4OjEyODowOjkw.",
                    description = "The Merovingian",
                    ruRole = "актеры",
                    enRole = "actor"
                )
            ),

            composer = listOf(
                Person(
                    id = 1799,
                    kpId = 608605,
                    ruName = "Джонни Клаймек",
                    enName = "Johnny Klimek",
                    photo = "https://cf4-static-eu1.stvcdn.org/img/cache/Nz/Qw/MmEwNzcyYTk2Y2I0MDppbWcvcGVyc29ucy83MC9mdC90d3pjZ3YxcC5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf2-static-eu1.stvcdn.org/img/cache/Nz/Qw/MmEwNzcyYTk2Y2I0MDppbWcvcGVyc29ucy83MC9mdC90d3pjZ3YxcC5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "композиторы",
                    enRole = "composer"
                ),
                Person(
                    id = 1800,
                    kpId = 26439,
                    ruName = "Том Тыквер",
                    enName = "Tom Tykwer",
                    photo = "https://cf1-static-eu1.stvcdn.org/img/cache/ZW/I4/NDdiZjhlNjcwOTkzYjppbWcvcGVyc29ucy8xZy9ieS95Znh4eGZlNC5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf1-static-eu1.stvcdn.org/img/cache/ZW/I4/NDdiZjhlNjcwOTkzYjppbWcvcGVyc29ucy8xZy9ieS95Znh4eGZlNC5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "композиторы",
                    enRole = "composer"
                )
            ),

            director = listOf(
                Person(
                    id = 1811,
                    kpId = 23330,
                    ruName = "Лана Вачовски",
                    enName = "Lana Wachowski",
                    photo = "https://cf3-static-eu1.stvcdn.org/img/cache/Nj/E3/MTI4OTkyZDc1YmRiNDppbWcvcGVyc29ucy9pNC9ici9ha2NqMDhpeS5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf1-static-eu1.stvcdn.org/img/cache/Nj/E3/MTI4OTkyZDc1YmRiNDppbWcvcGVyc29ucy9pNC9ici9ha2NqMDhpeS5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "режиссеры",
                    enRole = "director"
                )
            ),

            producer = listOf(
                Person(
                    id = 1811,
                    kpId = 23330,
                    ruName = "Лана Вачовски",
                    enName = "Lana Wachowski",
                    photo = "https://cf3-static-eu1.stvcdn.org/img/cache/Nj/E3/MTI4OTkyZDc1YmRiNDppbWcvcGVyc29ucy9pNC9ici9ha2NqMDhpeS5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf1-static-eu1.stvcdn.org/img/cache/Nj/E3/MTI4OTkyZDc1YmRiNDppbWcvcGVyc29ucy9pNC9ici9ha2NqMDhpeS5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "продюсеры",
                    enRole = "producer"
                ),
                Person(
                    id = 1815,
                    kpId = 31351,
                    ruName = "Грант Хилл",
                    enName = "Grant Hill",
                    photo = "https://cf3-static-eu1.stvcdn.org/img/cache/YW/Vl/ZmRkMzc5Njg0ODMxNjppbWcvcGVyc29ucy82ay91Yy9ndDd4NTFuMi5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf2-static-eu1.stvcdn.org/img/cache/YW/Vl/ZmRkMzc5Njg0ODMxNjppbWcvcGVyc29ucy82ay91Yy9ndDd4NTFuMi5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "продюсеры",
                    enRole = "producer"
                ),
                Person(
                    id = 1816,
                    kpId = 663055,
                    ruName = "Джеймс МакТиг",
                    enName = "James McTeigue",
                    photo = "https://cf4-static-eu1.stvcdn.org/img/cache/NT/Rk/MTc3ZTY2NzNmMzU1OTppbWcvcGVyc29ucy85My83Yi9sdWRja291OC5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf1-static-eu1.stvcdn.org/img/cache/NT/Rk/MTc3ZTY2NzNmMzU1OTppbWcvcGVyc29ucy85My83Yi9sdWRja291OC5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "продюсеры",
                    enRole = "producer"
                ),
                Person(
                    id = 1817,
                    kpId = 33584,
                    ruName = "Гаррет Грант",
                    enName = "Garrett Grant",
                    photo = "https://cf1-static-eu1.stvcdn.org/img/cache/OD/Ji/NDQ4MjYwMWRkZjE1YzppbWcvcGVyc29ucy83MC95bi81ZDgyb3M1dS5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf3-static-eu1.stvcdn.org/img/cache/OD/Ji/NDQ4MjYwMWRkZjE1YzppbWcvcGVyc29ucy83MC95bi81ZDgyb3M1dS5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "продюсеры",
                    enRole = "producer"
                )
            ),

            writer = listOf(
                Person(
                    id = 1811,
                    kpId = 23330,
                    ruName = "Лана Вачовски",
                    enName = "Lana Wachowski",
                    photo = "https://cf3-static-eu1.stvcdn.org/img/cache/Nj/E3/MTI4OTkyZDc1YmRiNDppbWcvcGVyc29ucy9pNC9ici9ha2NqMDhpeS5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf1-static-eu1.stvcdn.org/img/cache/Nj/E3/MTI4OTkyZDc1YmRiNDppbWcvcGVyc29ucy9pNC9ici9ha2NqMDhpeS5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "редакторы",
                    enRole = "writer"
                ),
                Person(
                    id = 1820,
                    kpId = 2345082,
                    ruName = "Дэвид Митчелл",
                    enName = "David Mitchell",
                    photo = "https://cf4-static-eu1.stvcdn.org/img/cache/ZD/li/YzgxZTA4MzRjZGEwYjppbWcvcGVyc29ucy9qbS8zeC80eWk3cnE1Yi5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf1-static-eu1.stvcdn.org/img/cache/ZD/li/YzgxZTA4MzRjZGEwYjppbWcvcGVyc29ucy9qbS8zeC80eWk3cnE1Yi5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "редакторы",
                    enRole = "writer"
                ),
                Person(
                    id = 1821,
                    kpId = 3289346,
                    ruName = "Александр Хемон",
                    enName = "Aleksandar Hemon",
                    photo = "https://cf2-static-eu1.stvcdn.org/img/cache/Mz/c5/MzRiM2UzOTFmZGI3ZDppbWcvcGVyc29ucy9jdS91ci9pZmlyMmI5NC5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf1-static-eu1.stvcdn.org/img/cache/Mz/c5/MzRiM2UzOTFmZGI3ZDppbWcvcGVyc29ucy9jdS91ci9pZmlyMmI5NC5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "редакторы",
                    enRole = "writer"
                ),
                Person(
                    id = 1822,
                    kpId = 23329,
                    ruName = "Лилли Вачовски",
                    enName = "Lilly Wachowski",
                    photo = "https://cf3-static-eu1.stvcdn.org/img/cache/M2/Y2/Y2Y3ZjBkYjg1ZDAzZDppbWcvcGVyc29ucy9xcS80di92aXY0dXBzcC5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf1-static-eu1.stvcdn.org/img/cache/M2/Y2/Y2Y3ZjBkYjg1ZDAzZDppbWcvcGVyc29ucy9xcS80di92aXY0dXBzcC5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "редакторы",
                    enRole = "writer"
                )
            ),

            editor = listOf(
                Person(
                    id = 1812,
                    kpId = 2010868,
                    ruName = "Джозеф Джетт Сэлли",
                    enName = "Joseph Jett Sally",
                    photo = "https://cf3-static-eu1.stvcdn.org/img/cache/YT/hl/OTFjZjcwMjNmMTM3ZTppbWcvcGVyc29ucy9iNi84ei9mMTU5ZzU5NC5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf2-static-eu1.stvcdn.org/img/cache/YT/hl/OTFjZjcwMjNmMTM3ZTppbWcvcGVyc29ucy9iNi84ei9mMTU5ZzU5NC5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "монтажеры",
                    enRole = "editor"
                )
            ),

            operator = listOf(
                Person(
                    id = 1813,
                    kpId = 629311,
                    ruName = "Даниэль Массаччеси",
                    enName = "Daniele Massaccesi",
                    photo = "https://cf2-static-eu1.stvcdn.org/img/cache/OD/Ew/ZWVmMTg4ZTg4Mzk3OTppbWcvcGVyc29ucy9ybC80MC83aHFmY3J3Yy5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf3-static-eu1.stvcdn.org/img/cache/OD/Ew/ZWVmMTg4ZTg4Mzk3OTppbWcvcGVyc29ucy9ybC80MC83aHFmY3J3Yy5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "операторы",
                    enRole = "operator"
                ),
                Person(
                    id = 1814,
                    kpId = 608759,
                    ruName = "Джон Толл",
                    enName = "John Toll",
                    photo = "https://cf3-static-eu1.stvcdn.org/img/cache/ND/Y4/ODNlODBiZTZjMmI1MDppbWcvcGVyc29ucy8xei9scy95dGcwcGVwdS5wbmc6MTI4OjEyODowOjkw.png",
                    photoAlt = "https://cf1-static-eu1.stvcdn.org/img/cache/ND/Y4/ODNlODBiZTZjMmI1MDppbWcvcGVyc29ucy8xei9scy95dGcwcGVwdS5wbmc6MTI4OjEyODowOjkw.",
                    description = null,
                    ruRole = "операторы",
                    enRole = "operator"
                )
            ),
        )
    )

    fun getFullMovie() = movie
}