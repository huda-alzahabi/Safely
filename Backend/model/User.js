const mongoose = require("mongoose");

const userSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true,
        min: 6,
        max: 255,
    },
    email: {
        type: String,
        required: true,
        min: 6,
        max: 255,
    },
    password: {
        type: String,
        required: true,
        min: 6,
        max: 1024,
    },
    token: {
        type: String,
        required: true,
        min: 25,
        max: 65535,
    },
    // usertype_id
});

module.exports = mongoose.model("User", userSchema);