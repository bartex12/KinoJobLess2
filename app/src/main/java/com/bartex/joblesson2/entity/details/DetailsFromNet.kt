package com.bartex.joblesson2.entity.details

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailsFromNet (
        @Expose val id:Int = 0,
        @Expose val  backdrop_path:String? = null,
        @Expose val  budget:Int = 0,
        @Expose val genres:List<Genres>,
        @Expose val overview:String? = null,
        @Expose val poster_path:String? = null,
        @Expose val release_date:String? = null,
        @Expose val runtime:Int? = null,
        @Expose val title:String ="",
        @Expose val vote_average:Float = 0.0f,
        @Expose val credits:Credits? = null
): Parcelable