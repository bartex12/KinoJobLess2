package com.bartex.joblesson2.repository

import com.bartex.joblesson2.api.IDataSource
import com.bartex.joblesson2.entity.details.DetailsFromNet
import com.bartex.joblesson2.entity.films.FilmsFromNet
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class FilmsRepoImpl(val dataSource: IDataSource): IFilmsRepo {

    override fun loadFilmsTopRatedRu(apiKey: String?,language: String, page: Int ): Single<FilmsFromNet> {
        return dataSource.loadFilmsTopRatedRu(apiKey, language, page)
            .subscribeOn(Schedulers.io())
    }

    override fun loadFilmsPopularRu(apiKey: String?,language: String,page: Int): Single<FilmsFromNet> {
        return dataSource.loadFilmsPopularRu(apiKey, language, page)
    }

    override fun loadFilmsDetailsRu(filmId: Int, apiKey: String?, language: String): Single<DetailsFromNet> {
        return dataSource.loadFilmsDetailsRu(filmId, apiKey, language )
    }

}


