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
   
    userType: {
        type: String,
        enum: ['Patient', 'Doctor','Hospital']
    },
   
});

module.exports = mongoose.model("User", userSchema);