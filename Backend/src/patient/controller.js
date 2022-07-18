const { addPatient } = require("./service");
const Patient = require("../../model/Patient");

async function add(req, res) {
  try {
    const newPatient = await addPatient(req.body);
    console.log("newPatient =>", newPatient);
    return res.status(200).send(newPatient); // 200
  } catch (error) {
    console.log(error);
    res.status(500).send(error);
  }
}
async function addLocation(req, res) {
  try {
    const patient = await Patient.findByIdAndUpdate(
      { _id: req.query.id },
      {
        $set: {
          locations: {
            longitude: req.body.longitude,
            latitude: req.body.latitude,
          },
        },
      }
    );
    return res.send("Patient Location Added");
  } catch (error) {
    console.log(error);
  }
}
module.exports = {
  add,
  addLocation
};
