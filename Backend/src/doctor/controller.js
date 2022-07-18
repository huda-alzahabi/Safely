const {
  addDoctor,
  getDoctorsByHospitalId,
  addAvailability,
} = require("./service");
const Hospital = require("../../model/Hospital");
const Doctor = require("../../model/Doctor");

async function addDr(req, res) {
  try {
    const newDoctor = await addDoctor(req.body);
    console.log("newDoctor =>", newDoctor);

    const updateHospital = await Hospital.updateOne(
      {
        _id: newDoctor.hospital,
      },
      {
        $push: {
          doctors: newDoctor._id,
        },
      }
    );
    console.log("updateHospital =>", updateHospital);

    return res.status(200).send(newDoctor);
  } catch (error) {
    console.log(error);
    res.status(500).send(error);
  }
}

async function getDoctors(req, res) {
  try {
    console.log(req.query);

    if (req.query.id) {
      const id = req.query.id;
      const result = await getDoctorsByHospitalId(id);
      console.log("result of specific hospital =>", result);
      return res.send(result);
    }
  } catch (error) {
    console.log(error);
  }
}

async function addDrAvailability(req, res) {
  try {
    const newAvailability = await addAvailability(req.body);
    console.log("newAvailability =>", newAvailability);
    const updateDr = await Doctor.updateOne(
      {
        _id: newAvailability.doctor,
      },
      {
        $push: {
          availability: newAvailability,
        },
      }
    );
    console.log("updateDr =>", updateDr);

    return res.status(200).send(newAvailability);
  } catch (error) {
    console.log(error);
    res.status(500).send(error);
  }
}

module.exports = {
  addDr,
  addDrAvailability,
  getDoctors,
};
