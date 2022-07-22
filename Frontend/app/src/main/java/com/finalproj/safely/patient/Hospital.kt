package com.finalproj.safely.patient

import com.finalproj.safely.user.UserInfo

data class Hospital(
    val phone_number: String,
    val address: Address,
    val hospital_location: Location,
    val user: UserInfo,
    val doctors: ArrayList<String>
) {
    override fun toString(): String {
        return "Hospital(phone_number='$phone_number',  address='$address',hospital_location='$hospital_location', user='$user', doctors='$doctors')"
    }

    data class Address(

        var country: String,
        var city: String,
        var street: String,
        var building: String,
    ) {
        override fun toString(): String {
            return "Address(country='$country', city='$city', street='$street', building='$building')"
        }
    }
}

