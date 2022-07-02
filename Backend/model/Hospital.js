const mongoose = require("mongoose");

const userTypeSchema = new mongoose.Schema({
    phone_number: {
        type: String,
        required: true,
        min: 6,
        max: 255,
    },
    //address:[ {}],
    //user_id
});

module.exports = mongoose.model("UserType", userTypeSchema);