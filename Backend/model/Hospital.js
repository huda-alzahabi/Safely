const mongoose = require("mongoose");

const addressSchema = new mongoose.Schema({
    country: {
        type: String,
        required: true,
        min: 6,
        max: 255,
    },
    city: {
        type: String,
        required: true,
        min: 6,
        max: 255,
    },
    street: {
        type: String,
        required: true,
        min: 6,
        max: 255,
    },
    building: {
        type: String,
        required: true,
        min: 6,
        max: 255,
    },
});


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
    address: addressSchema,

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