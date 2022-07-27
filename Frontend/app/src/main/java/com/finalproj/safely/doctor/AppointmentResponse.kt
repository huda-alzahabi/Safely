package com.finalproj.safely.doctor

data class AppointmentResponse(
    val _id: String,
    val day: String,
    val time: String,
    val hospital_name: String,
    val hospital_id: String,
    val patient_name: String,
    val patient_id: String,
    val doctor_name: String,
    val doctor_id: String,
)
