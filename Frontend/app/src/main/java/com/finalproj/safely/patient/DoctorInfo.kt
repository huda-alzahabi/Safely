package com.finalproj.safely.patient

import com.google.gson.annotations.SerializedName

data class DoctorInfo(
    @SerializedName("specialty")  val specialty: String,
    @SerializedName("years_of_experience") val years_of_experience: String,
    @SerializedName("hospital") var hospital: String,
    @SerializedName("user") var user: String,
)
