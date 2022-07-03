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
        unique: true,
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
    userType: {
        type: mongoose.Schema.Types.ObjectId,
        ref: "UserType",
    },
});

module.exports = mongoose.model("User", userSchema);