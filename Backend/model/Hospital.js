const mongoose = require("mongoose");

const userTypeSchema = new mongoose.Schema({
    phone_number: {
        type: String,
        required: true,
        min: 6,
        max: 255,
    },
    user: {
        type: mongoose.Schema.Types.ObjectId,
        ref: "User",
    },

    address: {
        type: mongoose.Schema.Types.ObjectId,
        ref: "Address",
    },
});

module.exports = mongoose.model("UserType", userTypeSchema);