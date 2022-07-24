const mongoose = require("mongoose");

const doctorAvailablitySchema = new mongoose.Schema({
    date: {
        type: String,
        required: true,
    },
    times: [{
        type: String,
        required: true,
        min: 5,
        max: 255,
    }],
    user_appointment:{
        user:{
            type: mongoose.Schema.Types.ObjectId,
            ref: "User",
        }, 
        available:{
        type: Boolean,
        default: true
        }
    },
    doctor: {
        type: mongoose.Schema.Types.ObjectId,
        ref: "Doctor",
    },
});
module.exports = mongoose.model("DrAvailability", doctorAvailablitySchema);