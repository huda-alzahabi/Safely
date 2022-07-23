const mongoose = require("mongoose");
const drAvailability = require("../model/DoctorAvailability");
const doctorSchema = new mongoose.Schema({
 
  specialty: {
    type: String,
    required: true,
  },
  years_of_experience: {
    type: String,
    required: true,
  },
  hospital: {
    type: mongoose.Schema.Types.ObjectId,
    ref: "Hospital",
  },
  user: {
    type: mongoose.Schema.Types.ObjectId,
    ref: "User",
  },

  availability: [drAvailability.schema],
});

module.exports = mongoose.model("Doctor", doctorSchema);
