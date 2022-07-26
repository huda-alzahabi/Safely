package com.finalproj.safely.patient

import com.google.gson.annotations.SerializedName

data class AppointmentInfo(
    @SerializedName("day") val day: String,
    @SerializedName("time") val time: String,
    @SerializedName("hospital_name") var hospital_name: String,
    @SerializedName("hospital_id") var hospital_id: String,
    @SerializedName("patient_name") var patient_name: String,
    @SerializedName("patient_id") var patient_id: String,
    @SerializedName("doctor_name") var doctor_name: String,
    @SerializedName("doctor_id") var doctor_id: String,
)
