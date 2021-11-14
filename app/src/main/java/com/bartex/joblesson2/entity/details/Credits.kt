package com.bartex.joblesson2.entity.details

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Credits (
    @Expose val cast:List<Persons>
):Parcelable