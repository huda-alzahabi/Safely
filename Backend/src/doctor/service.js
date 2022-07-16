const Doctor = require("../../model/Doctor");

async function addDoctor(req) {

    var availability = [];
    availability.push(req.body.availability);
    const doctor = new Doctor({
        name:req.body.name,
        specialty:req.body.specialty,
        hospital:req.body.hospital,
        availability
    });

    return await doctor.save();
}

  async function getDoctorsByHospitalId(hospital_id) {
    return await Doctor.find({ hospital: hospital_id });
  }

module.exports = {
    addDoctor,
    getDoctorsByHospitalId
};