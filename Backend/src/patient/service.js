const Patient = require("../../model/Patient");
const Appointment = require("../../model/Appointment");

async function addPatient(body) {
  const {
    phone_number,
    date_of_birth,
    nationality,
    sex,
    marital_status,
    user,
  } = body;

  const patient = new Patient({
    phone_number,
    date_of_birth,
    nationality,
    sex,
    marital_status,
    user,
  });

  return await patient.save();
}
async function addAppointment(body) {
  const {
    day,
    time,
    hospital_name,
    hospital_id,
    patient_name,
    patient_id,
    doctor_name,
    doctor_id,
  } = body;

  const appointment = new Appointment({
    day,
    time,
    hospital_name,
    hospital_id,
    patient_name,
    patient_id,
    doctor_name,
    doctor_id,
  });

  return await appointment.save();
}
async function getPatients() {
  return await Patient.find();
}

async function getById(id) {
  return await Patient.findById(id);
}

module.exports = {
  addPatient,
  addAppointment,
  getPatients,
  getById
};
