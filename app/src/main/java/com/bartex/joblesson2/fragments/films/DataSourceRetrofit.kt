package com.bartex.joblesson2.fragments.films

import com.bartex.joblesson2.entity.Constants
import com.bartex.joblesson2.entity.details.DetailsFromNet
import com.bartex.joblesson2.entity.films.FilmsFromNet
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DataSourceRetrofit :IDataSource{

    private val API: ApiService

    init {
        API = getDataSource()
    }
    override fun loadFilmsTopRatedRu(apiKey: String?,language: String, page: Int ): Single<FilmsFromNet> {
        return  API.getFilmsTopRatedRu(apiKey, language, page)
    }

    override fun loadFilmsPopularRu(apiKey: String?,language: String,page: Int): Single<FilmsFromNet> {
        return  API.getFilmsPopularRu(apiKey, language, page)
    }

    override fun loadFilmsDetailsRu(id:Int, apiKey: String?, language: String): Single<DetailsFromNet> {
        return  API.getFilmsDetailsRu(id, apiKey, language)
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