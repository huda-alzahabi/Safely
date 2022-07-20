const Hospital = require("../../model/Hospital");
const Address = require("../../model/Address");

async function addHospital(body) {
  const { phone_number, user, country, city, street, building  } = body;

  const hospital = new Hospital({
    phone_number,
    user,
    address:{
      country,city,street,building
    }

  });

  return await hospital.save();
}
module.exports = {
  addHospital,
};
