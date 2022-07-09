const { addDoctorAvailability,addDoctor } = require("./service");
const Doctor = require("../../model/Doctor");
const Hospital = require("../../model/Hospital");


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

async function addAvailability(req, res) {
  try {
    const newDoctorAvailability = await addDoctorAvailability(req.body);
    console.log("newDoctorAvailability =>", newDoctorAvailability);

    const updateDoctor = await Doctor.updateOne(
      {
        _id: newDoctorAvailability.doctor,
      },
      {
        $push: {
          availability: newDoctorAvailability._id,
        },
      }
    );
    console.log("updateDoctor =>", updateDoctor);

    return res.status(200).send(newDoctorAvailability); // 200
  } catch (error) {
    console.log(error);
    res.status(500).send(error);
  }
}

module.exports = {
    addDr,
  addAvailability,
};