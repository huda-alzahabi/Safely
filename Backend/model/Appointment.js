const mongoose = require("mongoose");

const appointmentSchema = new mongoose.Schema({
    examination_room: {
        type: String,
        required: true,
        min: 6,
        max: 255,
    },
    date: {
        type: Date,
        required: true,
        min: "2022-07-28",
        max: "2022-09-23",
    },
    start_time: {
        type: String,
        required: true,
        min: 5,
        max: 255,
    },
    end_time: {
        type: String,
        required: true,
        min: 5,
        max: 255,
    },

    //  hospital_id
    //  patient_id
    //  doctor_id
});

module.exports = mongoose.model("Appointment", appointmentSchema);