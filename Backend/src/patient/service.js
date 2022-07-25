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
        date,
        time,
        hospital_name,
        hospital_id,
        patient_name,
        patient_id,
        doctor_name,
        doctor_id
    } = body;

    const appointment = new Appointment({
        date,
        time,
        hospital_name,
        hospital_id,
        patient_name,
        patient_id,
        doctor_name,
        doctor_id
    });

    return await appointment.save();
}

module.exports = {
    addPatient,
    addAppointment
};