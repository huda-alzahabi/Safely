const mongoose = require("mongoose");
const addressSchema=require("../model/Address").schema;

const hospitalSchema = new mongoose.Schema({
    phone_number: {
        type: String,
        required: false,
        min: 6,
        max: 255,
    },
    user: {
        type: mongoose.Schema.Types.ObjectId,
        ref: "User",
    },
    address: [addressSchema],

    locations: {
        longitude:{
            type: String,
            required: false,
        }
        ,
        latitude:{
            type: String,
            required: false,
        }
    },
    doctors:
        [{
            type: mongoose.Schema.Types.ObjectId,
            ref: "Doctor",
        }],
    
});

module.exports = mongoose.model("Hospital", hospitalSchema);