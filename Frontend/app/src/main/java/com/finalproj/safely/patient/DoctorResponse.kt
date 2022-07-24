package com.finalproj.safely.patient

import com.finalproj.safely.user.UserInfo

data class DoctorResponse(
    var _id: String,
    var specialty: String,
    var years_of_experience: String,
    var hospital: String,
    var availability: ArrayList<DrAvailabilityResponse>,
    var user: UserInfo

    ) {

    override fun toString(): String {
        return "Doctor(specialty='$specialty',years_of_experience='$years_of_experience',hospital='$hospital', user='$user' ,_id='$_id',availability='$availability')"
    }



}