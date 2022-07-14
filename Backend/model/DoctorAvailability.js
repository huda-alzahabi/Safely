const mongoose = require("mongoose");

const doctorAvailablitySchema = new mongoose.Schema({
    date: {
        type: Date,
        required: true,
        min: "2022-07-28",
        max: "2022-09-23",
    },
    start_time: {
        type: String,
        required: true,
        min: 5,
        max: 255,
    },
    end_time: {
        type: String,
        required: true,
        min: 5,
        max: 255,
    },
    user_appointment:{
        user:{
            type: mongoose.Schema.Types.ObjectId,
            ref: "User",
        }, 
        available:{
        type: Boolean,
        default: true
        }

    }
});

module.exports = mongoose.model("DoctorAvailability", doctorAvailablitySchema);