const { addPatient } = require("./service");
const Patient = require("../../model/Patient");

async function add(req, res) {
  try {
    const newPatient = await addPatient(req.body);
    console.log("newPatient =>", newPatient);
    return res.status(200).send(newPatient); 
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
async function addMedicalRecords(req, res) {
  try {
    const patient = await Patient.findByIdAndUpdate(
      { _id: req.query.id },
      {
        $set: {
          medical_records: {
            current_medications: req.body.current_medications,
            chronic_diseases: req.body.chronic_diseases,
            allergies: req.body.allergies,
            blood_type: req.body.blood_type,
            weight: req.body.weight,
            height: req.body.height,
          },
        },
      }
    );
    return res.send({ message: "Records Added" });
  } catch (error) {
    console.log(error);
  }
}

module.exports = {
  add,
  addLocation,
  addMedicalRecords,
};
