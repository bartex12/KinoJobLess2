package com.bartex.joblesson2.entity.details

sealed class DetailsSealed {
    data class Success(val details: DetailsFromNet): DetailsSealed()
    data class Error(val error:Throwable): DetailsSealed()
    data class Loading(val progress:Int?): DetailsSealed()
}