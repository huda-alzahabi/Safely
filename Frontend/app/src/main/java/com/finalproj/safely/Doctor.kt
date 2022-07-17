package com.finalproj.safely

data class Doctor(
    var id: String,
    var name: String,
    var image: String,
    var speciality: String,

    ) {

    override fun toString(): String {
        return "Doctor(id='$id', image='$image', name='$name', speciality='$speciality')"
    }


}