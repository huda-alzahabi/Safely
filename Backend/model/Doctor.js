const mongoose = require("mongoose");
const drAvailability=require("../model/DoctorAvailability");
const doctorSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true,
        min: 6,
        max: 255,
    },
    specialty: {
        type: String,
        required: true,
        min: 6,
        max: 255,
    },
    hospital: {
        type: mongoose.Schema.Types.ObjectId,
        ref: "Hospital",
    },
 
    availability:[drAvailability.schema]
});

module.exports = mongoose.model("Doctor", doctorSchema);