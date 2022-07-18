package com.finalproj.safely

data class Doctor(
    var _id: String,
    var name: String,
    var image: String,
    var specialty: String,
    var availability: ArrayList<Availability>

    ) {

    override fun toString(): String {
        return "Doctor(id='$_id',  name='$name',image='$image', specialty='$specialty',availability='$availability')"
    }
    data class Availability(
        var _id: String,
        var doctor_id: String,
        var date: String,
        var time: String,
        var user_appointment: AppointmentStatus
    ) {
        override fun toString(): String {
            return "Availability(id='$_id', doctor_id='$doctor_id', time='$time', date='$date')"
        }
        data class AppointmentStatus(
            var user: String,
            var available: Boolean
        ) {
            override fun toString(): String {
                return "Appointment(user_id='$user', available='$available')"
            }
        }
    }


}