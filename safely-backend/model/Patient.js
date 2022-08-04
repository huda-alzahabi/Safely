const mongoose = require("mongoose");

const patientSchema = new mongoose.Schema({
  phone_number: {
    type: String,
    required: false,
    min: 6,
    max: 255,
  },
  date_of_birth: {
    type: String,
    required: false,
    min: 10,
    max: 255,
  },
  nationality: {
    type: String,
    required: false,
    min: 5,
    max: 255,
  },
  sex: {
    type: String,
    required: false,
    min: 4,
    max: 255,
  },
  marital_status: {
    type: String,
    required: false,
    min: 6,
    max: 255,
  },
  user: {
    type: mongoose.Schema.Types.ObjectId,
    ref: "User",
  },
  location: {
    longitude: {
      type: String,
      required: false,
    },
    latitude: {
      type: String,
      required: false,
    },
  },
  medical_records: {
    current_medications: {
      type: String,
      required: false,
    },
    chronic_diseases: {
      type: String,
      required: false,
    },
    allergies: {
      type: String,
      required: false,
    },
    blood_type: {
      type: String,
      required: false,
    },
    weight: {
      type: String,
      required: false,
    },
    height: {
      type: String,
      required: false,
    },
  }
});

module.exports = mongoose.model("Patient", patientSchema);
