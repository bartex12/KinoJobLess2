package com.bartex.joblesson2.entity.films

sealed class FilmSealed{
    data class Success(val films: FilmsFromNet): FilmSealed()
    data class Error(val error:Throwable): FilmSealed()
    data class Loading(val progress:Int?): FilmSealed()
}
