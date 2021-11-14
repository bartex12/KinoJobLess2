package com.bartex.joblesson2.fragments.films

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bartex.joblesson2.App
import com.bartex.joblesson2.api.DataSourceRetrofit
import com.bartex.joblesson2.entity.Constants
import com.bartex.joblesson2.entity.films.FilmSealed
import com.bartex.joblesson2.helper.IPreferenceHelper
import com.bartex.joblesson2.helper.PreferenceHelper
import com.bartex.joblesson2.repository.FilmsRepoImpl
import com.bartex.joblesson2.repository.IFilmsRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class FilmsViewModel(
        var helper : IPreferenceHelper = PreferenceHelper(App.instance),
        private val filmsRepo: IFilmsRepo = FilmsRepoImpl(dataSource = DataSourceRetrofit())
): ViewModel() {

    private val listOfFilms = MutableLiveData<FilmSealed>()

    fun getFilmsSealed():LiveData<FilmSealed>{
        return listOfFilms
    }

     fun loadFilmsSealed(page:Int) {
        listOfFilms.value = FilmSealed.Loading(0)
        filmsRepo.loadFilmsPopularRu( page = page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {listFilms->
                    listOfFilms.value = FilmSealed.Success(films=listFilms)
                },
                {error->
                    listOfFilms.value =  FilmSealed.Error(error = error)
                })
    }

    fun savePosition(position: Int){
        helper.savePosition(position)
    }

    fun getPosition(): Int{
        return helper.getPosition()
    }

    fun savePage(page: Int) {
        return helper.savePage(page)
    }

    fun getPage(): Int{
        return helper.getPage()
    }
}