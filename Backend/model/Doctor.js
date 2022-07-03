const mongoose = require("mongoose");

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
});

module.exports = mongoose.model("Doctor", doctorSchema);