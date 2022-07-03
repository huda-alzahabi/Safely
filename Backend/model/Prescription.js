const mongoose = require("mongoose");

const prescriptionSchema = new mongoose.Schema({
    diagnosis: {
        type: String,
        required: true,
        min: 6,
        max: 255,
    },
    drug: {
        type: String,
        required: true,
        min: 6,
        max: 255,
    },
    prescription_date: {
        type: Date,
        required: true,
        min: "2022-07-28",
        max: "2022-09-23",
    },
    patient: {
        type: mongoose.Schema.Types.ObjectId,
        ref: "Patient",
    },
    doctor: {
        type: mongoose.Schema.Types.ObjectId,
        ref: "Doctor",
    },
});

module.exports = mongoose.model("Prescription", prescriptionSchema);