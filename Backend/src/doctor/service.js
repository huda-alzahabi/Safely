const Doctor = require("../../model/Doctor");
const DrAvailability = require("../../model/DoctorAvailability");

async function addDoctor(body) {
  const { specialty, years_of_experience, hospital, user } = body;

  const doctor = new Doctor({
    specialty,
    years_of_experience,
    hospital,
    user
  });

  return await doctor.save();
}
async function getDoctorsByHospitalId(hospital_id) {
  return await Doctor.find({ hospital: hospital_id }).populate("user");
}
async function addAvailability(body) {
  const { day, times, doctor } = body;

  const availability = new DrAvailability({
    day,
    times,
    doctor,
  });
  console.log("availability =>", availability);
  return await availability;
}

module.exports = {
  addDoctor,
  addAvailability,
  getDoctorsByHospitalId,
};
