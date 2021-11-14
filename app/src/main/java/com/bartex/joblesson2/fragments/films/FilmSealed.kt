package com.bartex.joblesson2.fragments.films

import com.bartex.joblesson2.entity.films.FilmsFromNet

sealed class FilmSealed{
    data class Success(val films: FilmsFromNet):FilmSealed()
    data class Error(val error:Throwable):FilmSealed()
    data class Loading(val progress:Int?):FilmSealed()
}
