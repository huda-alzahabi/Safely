const Doctor = require("../../model/Doctor");
const DoctorAvailability = require("../../model/DoctorAvailability");


async function addDoctor(body) {
    const { name, specialty,hospital,availability } = body;

    const doctor = new Doctor({
        name,
        specialty,
        hospital,
        availability 
    });

    return await doctor.save();
}

async function addDoctorAvailability(body) {
    const {
      date,
      start_time,
      end_time,
      doctor,
      user_appointment,
    } = body;
  
    const doctorAvailability = new DoctorAvailability({
      date,
      start_time,
      end_time,
      doctor,
      user_appointment,
    });
  
    return await doctorAvailability.save();
  }

module.exports = {
    addDoctor,
    addDoctorAvailability,
};