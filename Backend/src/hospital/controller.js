const { addHospital,addAddress } = require("./service");
const Hospital = require("../../model/Hospital");
const Address = require("../../model/Address");

async function add(req, res) {
  try {
    const newHospital = await addHospital(req.body);
    console.log("newHospital =>", newHospital);
    return res.status(200).send(newHospital); // 200
  } catch (error) {
    console.log(error);
    res.status(500).send(error);
  }
}
async function addHospitalAddress(req, res) {
  try {
    const newAddress = await addAddress(req.body);
    console.log("newAddress =>", newAddress);
    const updateHospital = await Hospital.updateOne(
      {
        _id: newAddress.hospital,
      },
      {
        $push: {
          address: newAddress,
        },
      }
    );
    console.log("updateHospital =>", updateHospital);

    return res.status(200).send(newAddress);
  } catch (error) {
    console.log(error);
    res.status(500).send(error);
  }
}

module.exports = {
  add,
  addHospitalAddress,
};
