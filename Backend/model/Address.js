const mongoose = require("mongoose");

const addressSchema = new mongoose.Schema({
    country: {
        type: String,
        required: true,
        min: 6,
        max: 255,
    },
    city: {
        type: String,
        required: true,
        min: 6,
        max: 255,
    },
    street: {
        type: String,
        required: true,
        min: 6,
        max: 255,
    },
    building: {
        type: String,
        required: true,
        min: 6,
        max: 255,
    },
    hospital: {
        type: mongoose.Schema.Types.ObjectId,
        ref: "Hospital",
    }
});

module.exports = mongoose.model("Address", addressSchema);