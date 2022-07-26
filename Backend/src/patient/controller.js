const { addPatient,addAppointment } = require("./service");
const Patient = require("../../model/Patient");
const Hospital = require("../../model/Hospital");
const Appointment = require("../../model/Appointment");


async function add(req, res) {
  try {
    const newPatient = await addPatient(req.body);
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
          location: {
            longitude: req.body.longitude,
            latitude: req.body.latitude,
          },
        },
      }
    );
    return res.send({ message: "Patient Location Added" });
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
async function findNearbyHospitals(req, res) {
  try {
  //get patient location
  const patient = await Patient.findById(req.query.id);
  const patientLatitude = patient.location.latitude;
  const patientLongitude = patient.location.longitude;
  //get all hospitals
  const hospitals = await Hospital.find().populate("user");
  //find hospitals within 25km
  const nearbyHospitals = hospitals.filter((hospital) => {
    const hospitalLatitude = hospital.location.latitude;
    const hospitalLongitude = hospital.location.longitude;
    const distance = getDistanceFromLatLonInKm(
      patientLatitude,
      patientLongitude,
      hospitalLatitude,
      hospitalLongitude
    );
    hospital.distance = distance.toFixed(2);
    return distance <= 25;
  }
  );
  return res.send(nearbyHospitals);
  }
  catch (error) {
    console.log(error);
  }
}
function getDistanceFromLatLonInKm(lat1, lon1, lat2, lon2) {
  var R = 6371; // Radius of the earth in km
  var dLat = degreesToRadians(lat2 - lat1); // deg2rad below
  var dLon = degreesToRadians(lon2 - lon1);
  var a =
    Math.sin(dLat / 2) * Math.sin(dLat / 2) +
    Math.cos(degreesToRadians(lat1)) *
      Math.cos(degreesToRadians(lat2)) *
      Math.sin(dLon / 2) *
      Math.sin(dLon / 2);
  var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
  var d = R * c; // Distance in km
  return d;
}

function degreesToRadians(degrees) {
  return degrees * Math.PI / 180;
}

async function bookAppointment(req, res) {
  try {
    const newAppointment = await addAppointment(req.body);
    return res.status(200).send({message: newAppointment._id});
  } catch (error) {
    console.log(error);
    res.status(500).send(error);
  }
}

async function getAppointmentsByPatientId(req, res) {
  try {
    if (req.query.id) {
      const id = req.query.id;
      const result = await Appointment.find({patient_id: id});
      console.log(result);
      return res.send(result);

    }
  } catch (error) {
    console.log(error);
  }
}

module.exports = {
  add,
  addLocation,
  addMedicalRecords,
  findNearbyHospitals,
  bookAppointment,
  getAppointmentsByPatientId
};
