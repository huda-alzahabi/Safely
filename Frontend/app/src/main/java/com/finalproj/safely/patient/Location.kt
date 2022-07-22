package com.finalproj.safely.patient

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("longitude") val longitude: String?,
    @SerializedName("latitude") val latitude: String?
)
