const mongoose = require("mongoose");

const appointmentSchema = new mongoose.Schema({
    date: {
        type: Date,
        required: true,
    },
    time: {
        type: String,
        required: true,
        min: 5,
        max: 255,
    },
    hospital_name: {
        type: String,
        required: true,
        min: 5,
        max: 255,
    },
    hospital_id:{
        type: String,
        required: true,
        min: 5,
        max: 255,
    },
    patient_name: {
        type: String,
        required: true,
        min: 5,
        max: 255,
    },
    patient_id: {
        type: String,
        required: true,
        min: 5,
        max: 255,
    },
    
    doctor_name: {
        type: String,
        required: true,
        min: 5,
        max: 255,
    },
    doctor_id: {
        type: String,
        required: true,
        min: 5,
        max: 255,
    },
});

module.exports = mongoose.model("Appointment", appointmentSchema);