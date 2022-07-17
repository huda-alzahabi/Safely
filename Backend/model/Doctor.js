const mongoose = require("mongoose");

const doctorAvailablitySchema = new mongoose.Schema({
    date: {
        type: Date,
        required: true,
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
const doctorSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true,
        min: 6,
        max: 255,
    },
    specialty: {
        type: String,
        required: true,
        min: 6,
        max: 255,
    },
    hospital: {
        type: mongoose.Schema.Types.ObjectId,
        ref: "Hospital",
    },
 
    availability:[{doctorAvailablitySchema}]
});

module.exports = mongoose.model("Doctor", doctorSchema);