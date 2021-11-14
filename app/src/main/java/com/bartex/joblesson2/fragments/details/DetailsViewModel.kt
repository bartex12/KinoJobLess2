package com.bartex.joblesson2.fragments.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bartex.joblesson2.entity.Constants
import com.bartex.joblesson2.api.DataSourceRetrofit
import com.bartex.joblesson2.entity.details.DetailsSealed
import com.bartex.joblesson2.repository.FilmsRepoImpl
import com.bartex.joblesson2.repository.IFilmsRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class DetailsViewModel(
        private val filmsRepo: IFilmsRepo = FilmsRepoImpl(dataSource = DataSourceRetrofit())
): ViewModel() {

    private val details = MutableLiveData<DetailsSealed>()

    fun getDetailsSealed(): LiveData<DetailsSealed> {
        return details
    }

     fun loadDetailSealed(id: Int) {
        details.value = DetailsSealed.Loading(0)
        filmsRepo.loadFilmsDetailsRu( filmId = id , apiKey = Constants.api_key,language ="ru-RU")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {listFilms->
                            details.value = DetailsSealed.Success(details= listFilms)
                        },
                        {error->
                            details.value =  DetailsSealed.Error(error = error)
                        })

    }
}