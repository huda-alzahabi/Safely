package com.finalproj.safely.hospital

import com.google.gson.annotations.SerializedName

data class HospitalInfo(
    @SerializedName("phone_number") val phone_number: String?,
    @SerializedName("country") val country: String?,
    @SerializedName("city") val city: String?,
    @SerializedName("street") val street: String?,
    @SerializedName("building") val building: String?,
    @SerializedName("user") val user: String?,
    @SerializedName("outpatient_clinic") val outpatient_clinic: String?,
)
