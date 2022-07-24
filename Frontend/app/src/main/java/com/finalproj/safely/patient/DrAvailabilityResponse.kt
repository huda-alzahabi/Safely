package com.finalproj.safely.patient

data class DrAvailabilityResponse(
    var _id: String,
    var doctor_id: String,
    var day: String,
    var time: ArrayList<String>,
    var user_appointment: AppointmentStatus,
) {
    override fun toString(): String {
        return "DrAvailabilityInfo(id='$_id', doctor_id='$doctor_id', time='$time', day='$day', user_appointment='$user_appointment')"
    }

    data class AppointmentStatus(
        var user: String,
        var available: Boolean,
    ) {
        override fun toString(): String {
            return "Appointment(user_id='$user', available='$available')"
        }
    }
}

