package com.bartex.joblesson2.fragments.films

import com.bartex.joblesson2.entity.details.DetailsFromNet
import com.bartex.joblesson2.entity.films.FilmsFromNet
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/top_rated")
    fun getFilmsTopRatedRu(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String = "ru-RU",
        @Query("page") page: Int = 1
    ): Single<FilmsFromNet>

    @GET("movie/popular")
    fun getFilmsPopularRu(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String = "ru-RU",
        @Query("page") page: Int = 1
    ): Single<FilmsFromNet>

    @GET("movie/{movie_id}")
    fun getFilmsDetailsRu(
            @Path("movie_id") movieId:Int,
            @Query("api_key") apiKey: String?,
            @Query("language") language: String = "ru-RU"
    ): Single<DetailsFromNet>


}