package com.bartex.joblesson2.api

import com.bartex.joblesson2.entity.details.DetailsFromNet
import com.bartex.joblesson2.entity.films.FilmsFromNet
import io.reactivex.rxjava3.core.Single

interface IDataSource {
    fun loadFilmsTopRatedRu(page: Int ): Single<FilmsFromNet>
    fun loadFilmsPopularRu(page: Int ): Single<FilmsFromNet>
    fun loadFilmsDetailsRu(id:Int ): Single<DetailsFromNet>
}