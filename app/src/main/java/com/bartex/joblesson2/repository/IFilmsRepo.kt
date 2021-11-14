package com.bartex.joblesson2.repository

import com.bartex.joblesson2.entity.details.DetailsFromNet
import com.bartex.joblesson2.entity.films.FilmsFromNet
import io.reactivex.rxjava3.core.Single

interface IFilmsRepo {
   fun loadFilmsTopRatedRu(page: Int ): Single<FilmsFromNet>
   fun loadFilmsPopularRu(page: Int ): Single<FilmsFromNet>
   fun loadFilmsDetailsRu(filmId:Int): Single<DetailsFromNet>
}