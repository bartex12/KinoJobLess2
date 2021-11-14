package com.bartex.joblesson2.api

import com.bartex.joblesson2.entity.Constants
import com.bartex.joblesson2.entity.details.DetailsFromNet
import com.bartex.joblesson2.entity.films.FilmsFromNet
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DataSourceRetrofit : IDataSource {

    private val API: ApiService

    init {
        API = getDataSource()
    }
    override fun loadFilmsTopRatedRu(page: Int ): Single<FilmsFromNet> {
        return  API.getFilmsTopRatedRu(page = page)
    }

    override fun loadFilmsPopularRu(page: Int): Single<FilmsFromNet> {
        return  API.getFilmsPopularRu(page = page)
    }

    override fun loadFilmsDetailsRu(id:Int): Single<DetailsFromNet> {
        return  API.getFilmsDetailsRu(movieId = id)
    }

    private fun getDataSource(): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .excludeFieldsWithoutExposeAnnotation()
                        .create()
                )).build().create(ApiService::class.java)
    }
}