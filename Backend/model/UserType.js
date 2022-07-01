const mongoose = require("mongoose");

const userTypeSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true,
        min: 6,
        max: 255,
    },
});

module.exports = mongoose.model("UserType", userTypeSchema);