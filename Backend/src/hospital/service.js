const Hospital = require("../../model/Hospital");
const Address = require("../../model/Address");

async function addHospital(body) {
  const { phone_number, user } = body;

  const hospital = new Hospital({
    phone_number,
    user,
  });

  return await hospital.save();
}
async function addAddress(body) {
  const { country, city, street, building, hospital } = body;

  const address = new Address({
    country,
    city,
    street,
    building,
    hospital,
  });
  console.log("address =>", address);
  return await address;
}
module.exports = {
  addHospital,
  addAddress,
};
