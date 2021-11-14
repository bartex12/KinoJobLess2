package com.bartex.joblesson2.entity.details

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genres(
        @Expose val  id :Int = 0,
        @Expose val name: String = ""
): Parcelable
