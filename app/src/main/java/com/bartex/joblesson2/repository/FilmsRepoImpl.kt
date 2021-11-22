package com.bartex.joblesson2.repository

import com.bartex.joblesson2.api.IDataSource
import com.bartex.joblesson2.entity.details.DetailsFromNet
import com.bartex.joblesson2.entity.films.FilmsFromNet
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class FilmsRepoImpl(val dataSource: IDataSource): IFilmsRepo {

    override fun loadFilmsTopRatedRu(page: Int ): Single<FilmsFromNet> {
        return dataSource.loadFilmsTopRatedRu(page = page)
            .subscribeOn(Schedulers.io())
    }

    override fun loadFilmsPopularRu(page: Int): Single<FilmsFromNet> {
        return dataSource.loadFilmsPopularRu(page)
                .subscribeOn(Schedulers.io())
    }

    override fun loadFilmsDetailsRu(filmId: Int): Single<DetailsFromNet> {
        return dataSource.loadFilmsDetailsRu(id = filmId)
                .subscribeOn(Schedulers.io())
    }

}


