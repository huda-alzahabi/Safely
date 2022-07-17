package com.finalproj.safely

data class Doctor(
    var _id: String,
    var name: String,
    var image: String,
    var specialty: String,

    ) {

    override fun toString(): String {
        return "Doctor(id='$_id',  name='$name',image='$image', specialty='$specialty')"
    }


}