package com.finalproj.safely

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token") val token: String?
)
