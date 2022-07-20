package com.finalproj.safely.patient

import com.google.gson.annotations.SerializedName

data class MedicalRecords(
    @SerializedName("current_medications") val current_medications: String?,
    @SerializedName("chronic_diseases") val chronic_diseases: String?,
    @SerializedName("allergies") val allergies: String?,
    @SerializedName("blood_type") val blood_type: String?,
    @SerializedName("weight") val weight: String?,
    @SerializedName("height") val height: String?,
)
