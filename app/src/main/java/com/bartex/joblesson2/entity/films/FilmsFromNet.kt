package com.bartex.joblesson2.entity.films

import android.os.Parcelable
import com.bartex.joblesson2.entity.films.Films
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmsFromNet(
        @Expose val total_pages:Int = 0,
        @Expose val page:Int = 0,
        @Expose val  results:List<Films>? = null
): Parcelable
