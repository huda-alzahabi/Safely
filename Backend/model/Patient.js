const mongoose = require("mongoose");

const patientSchema = new mongoose.Schema({
  phone_number: {
    type: String,
    required: true,
    min: 6,
    max: 255,
  },
  date_of_birth: {
    type: String,
    required: true,
    min: 10,
    max: 255,
  },
  nationality: {
    type: String,
    required: true,
    min: 5,
    max: 255,
  },
  sex: {
    type: String,
    required: true,
    min: 4,
    max: 255,
  },
  marital_status: {
    type: String,
    required: true,
    min: 6,
    max: 255,
  },
  user: {
    type: mongoose.Schema.Types.ObjectId,
    ref: "User",
  },
  locations: [
    {
      longitude: {
        type: String,
        required: true,
      },
      latitude: {
        type: String,
        required: true,
      },
    },
  ],
  medical_records: 
    {
      current_medications: {
        type: String,
        required: true,
      },
      chronic_diseases: {
        type: String,
        required: true,
      },
      allergies: {
        type: String,
        required: true,
      },
      blood_type: {
        type: String,
        required: true,
      },
      weight: {
        type: String,
        required: true,
      },
      height: {
        type: String,
        required: true,
      },
    },
  
});

module.exports = mongoose.model("Patient", patientSchema);
