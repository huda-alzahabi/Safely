const mongoose = require("mongoose");

const addressSchema = new mongoose.Schema({
    country: {
        type: String,
        required: false,
        min: 6,
        max: 255,
    },
    city: {
        type: String,
        required: false,
        min: 6,
        max: 255,
    },
    street: {
        type: String,
        required: false,
        min: 6,
        max: 255,
    },
    building: {
        type: String,
        required: false,
        min: 6,
        max: 255,
    },
});

module.exports = mongoose.model("Address", addressSchema);