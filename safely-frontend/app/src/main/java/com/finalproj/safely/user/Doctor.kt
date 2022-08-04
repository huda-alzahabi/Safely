package com.finalproj.safely.user

import com.finalproj.safely.patient.DrAvailabilityResponse
import com.finalproj.safely.user.UserInfo
import com.google.gson.annotations.SerializedName

data class Doctor(
    @SerializedName("_id") val _id: String,
    @SerializedName("specialty") val specialty: String,
    @SerializedName("years_of_experience") val years_of_experience: String,
    @SerializedName("hospital") val hospital: String,
    @SerializedName("availability") val availability: ArrayList<DrAvailabilityResponse>,
    @SerializedName("user") val user: String,

    ) {

    override fun toString(): String {
        return "Doctor(specialty='$specialty',years_of_experience='$years_of_experience',hospital='$hospital', user='$user' ,_id='$_id',availability='$availability')"
    }


}