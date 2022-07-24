package com.finalproj.safely.doctor

import com.google.gson.annotations.SerializedName

data class Availability(
    @SerializedName("day")  val day: String,
    @SerializedName("times") val times: MutableList<String>,
    @SerializedName("doctor") var doctor: String,
)
