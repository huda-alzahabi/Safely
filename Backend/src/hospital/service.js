const Hospital = require("../../model/Hospital");

async function addHospital(body) {
  const { phone_number, user, country, city, street, building } = body;

  const hospital = new Hospital({
    phone_number,
    user,
    address: {
      country,
      city,
      street,
      building,
    },
  });

  return await hospital.save();
}
async function getHospitals() {
  return await Hospital.find().populate("user");
}
async function getHospitalById(id) {
  return await Hospital.findById(id);
}
module.exports = { getHospitalById, getHospitals, addHospital };
