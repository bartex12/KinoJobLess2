package com.bartex.joblesson2.fragments.films

import com.bartex.joblesson2.entity.details.DetailsFromNet
import com.bartex.joblesson2.entity.films.FilmsFromNet
import io.reactivex.rxjava3.core.Single

interface IDataSource {
    fun loadFilmsTopRatedRu(apiKey: String?,language: String, page: Int ): Single<FilmsFromNet>
    fun loadFilmsPopularRu(apiKey: String?,language: String, page: Int ): Single<FilmsFromNet>
    fun loadFilmsDetailsRu(id:Int, apiKey: String?,language: String ): Single<DetailsFromNet>
}