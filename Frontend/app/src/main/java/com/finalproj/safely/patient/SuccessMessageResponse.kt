package com.finalproj.safely.patient

import com.google.gson.annotations.SerializedName

data class SuccessMessageResponse(
    @SerializedName("message") val message: String?,
)
