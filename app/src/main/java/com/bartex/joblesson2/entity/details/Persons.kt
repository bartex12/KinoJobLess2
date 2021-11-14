package com.bartex.joblesson2.entity.details

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Persons(
    @Expose val id :Int? = null,
    @Expose val original_name :String? = null,
    @Expose val profile_path :String? = null,
    @Expose val character :String? = null
): Parcelable
