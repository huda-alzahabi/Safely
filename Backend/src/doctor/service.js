const Doctor = require("../../model/Doctor");
const DrAvailability = require("../../model/DoctorAvailability");

async function addDoctor(body) {
  const { name, specialty, hospital } = body;

  const doctor = new Doctor({
    name,
    specialty,
    hospital,
  });

  return await doctor.save();
}
async function getDoctorsByHospitalId(hospital_id) {
  return await Doctor.find({ hospital: hospital_id });
}
async function addAvailability(body) {
  const { date, time, doctor } = body;

  const availability = new DrAvailability({
    date,
    time,
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
