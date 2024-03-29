package com.finalproj.safely.user

import com.google.gson.annotations.SerializedName


data class UserInfo(
    @SerializedName("name") val name: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("password") val password: String?,
    @SerializedName("userType") val userType: String?,

    )