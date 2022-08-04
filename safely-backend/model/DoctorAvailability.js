const mongoose = require("mongoose");

const doctorAvailablitySchema = new mongoose.Schema({
    day: {
        type: String,
        required: false,
    },
    times: [{
        type: String,
        required: false,
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
        default: false
        }
    },
    doctor: {
        type: mongoose.Schema.Types.ObjectId,
        ref: "Doctor",
    },
});
module.exports = mongoose.model("DrAvailability", doctorAvailablitySchema);