const mongoose = require("mongoose");
const HospitalAddress = require("./Address");

const hospitalSchema = new mongoose.Schema({
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
    address: {HospitalAddress},
    
    locations: [{
        longitude:{
            type: String,
            required: true,
        }
        ,
        latitude:{
            type: String,
            required: true,
        }
    }],
    doctors:
        [{
            type: mongoose.Schema.Types.ObjectId,
            ref: "Doctor",
        }],
    
});

module.exports = mongoose.model("Hospital", hospitalSchema);