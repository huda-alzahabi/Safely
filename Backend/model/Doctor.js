const mongoose = require("mongoose");
const DoctorAvailability = require("./DoctorAvailability");

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
 
    availability: {DoctorAvailability}
});

module.exports = mongoose.model("Doctor", doctorSchema);