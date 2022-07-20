package com.finalproj.safely.user

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token") val token: String?
)
