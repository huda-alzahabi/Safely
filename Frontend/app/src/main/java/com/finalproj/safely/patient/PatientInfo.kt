package com.finalproj.safely.patient

import com.google.gson.annotations.SerializedName

data class PatientInfo(
    @SerializedName("phone_number") val phone_number: String?,
    @SerializedName("date_of_birth") val date_of_birth: String?,
    @SerializedName("nationality") val nationality: String?,
    @SerializedName("sex") val sex: String?,
    @SerializedName("marital_status") val marital_status: String?,
    @SerializedName("user") val user: String?,
)
