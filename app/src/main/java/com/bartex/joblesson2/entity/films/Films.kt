package com.bartex.joblesson2.entity.films

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Films (
    @Expose val id:Int = 0,
    @Expose val title:String? = null,
    @Expose val overview:String? = null,
    @Expose val poster_path:String? = null,
    @Expose val release_date:String? = null,
    @Expose val backdrop_path:String? = null,
):Parcelable


