const {
  addDoctor,
  getDoctorsByHospitalId,
  addAvailability,
} = require("./service");
const Hospital = require("../../model/Hospital");
const Doctor = require("../../model/Doctor");
const Availability = require("../../model/DoctorAvailability");
const Appointment = require("../../model/Appointment");


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

    return res.status(200).send({ message: newDoctor._id });
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
async function getAvailabilityByDrId(req, res) {
  try {
    if (req.query.id) {
      const id = req.query.id;
      const result = await Doctor.findById(id);
      console.log(result.availability);
      return res.send(result.availability);
    }
  } catch (error) {
    console.log(error);
  }
}
async function getTimesByAvailabilityId(req, res) {
  try {
    if (req.query.id) {
      const id = req.query.id;
      const result = await Availability.findById(id);
      console.log(result.times);
      return res.send({times:result.times});
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

    return res.status(200).send({ message: "Availability Added" });
  } catch (error) {
    console.log(error);
    res.status(500).send(error);
  }
}

async function getAppointmentsByDrId(req, res) {
  try {
    if (req.query.id) {
      const id = req.query.id;
      const result = await Appointment.find({doctor_id: id});
      console.log(result);
      return res.send(result);

    }
  } catch (error) {
    console.log(error);
  }
}



module.exports = {
  addDr,
  addDrAvailability,
  getDoctors,
  getAvailabilityByDrId,
  getTimesByAvailabilityId,
  getAppointmentsByDrId
};
