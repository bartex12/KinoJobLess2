package com.bartex.joblesson2.fragments.films

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bartex.joblesson2.App
import com.bartex.joblesson2.entity.Constants
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import retrofit2.http.Query


class FilmsViewModel(
        var helper : IPreferenceHelper = PreferenceHelper(App.instance),
        private val filmsRepo:IFilmsRepo = FilmsRepoImpl(dataSource = DataSourceRetrofit())
): ViewModel() {

    private val listOfFilms = MutableLiveData<FilmSealed>()

    fun getFilmsSealed():LiveData<FilmSealed>{
        return listOfFilms
    }

     fun loadFilmsSealed(page:Int) {
        listOfFilms.value = FilmSealed.Loading(0)
//        filmsRepo.loadFilmsTopRatedRu( apiKey = Constants.api_key,language ="ru-RU",page = 1)
        filmsRepo.loadFilmsPopularRu( apiKey = Constants.api_key,language =Constants.LANG_RU,page = page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {listFilms->
                    listOfFilms.value = FilmSealed.Success(films=listFilms)
                    Log.d(TAG, "FilmsViewModel onSuccess listFilms.size = ${listFilms.results?.size}")
                },
                {error->
                    listOfFilms.value =  FilmSealed.Error(error = error)
                    Log.d(TAG, "FilmsViewModel onError ${error.message}")
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

    companion object{
        const val TAG = "33333"
    }
}