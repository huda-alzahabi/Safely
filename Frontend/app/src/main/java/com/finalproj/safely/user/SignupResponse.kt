package com.finalproj.safely.user

import com.google.gson.annotations.SerializedName

data class SignupResponse(
    @SerializedName("user")
    var user: String,
)
