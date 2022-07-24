package com.finalproj.safely.patient

data class DrAvailabilityResponse(
    var user_appointment: AppointmentStatus,
    var day: String,
    var times: ArrayList<String>,
    var doctor: String,
    var _id: String,


) {
    override fun toString(): String {
        return "DrAvailabilityResponse(user_appointment=$user_appointment, day='$day', times=$times, doctor='$doctor', _id='$_id')"
    }

    data class AppointmentStatus(
        var user: String,
        var available: Boolean,
    ) {
        override fun toString(): String {
            return "user_appointment(user='$user', available='$available')"
        }
    }
}

