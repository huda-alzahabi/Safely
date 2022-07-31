const { addHospital, getHospitals, getHospitalById } = require("./service");
const Hospital = require("../../model/Hospital");
const User = require("../../model/User");

async function add(req, res) {
  try {
    const newHospital = await addHospital(req.body);
    console.log("newHospital =>", newHospital);
    return res.status(200).send({ message: newHospital._id });
  } catch (error) {
    console.log(error);
    res.status(500).send(error);
  }
}

async function get(req, res) {
  try {
    console.log(req.query);

    if (req.query.id) {
      const id = req.query.id;
      const result = await getHospitalById(id);
      console.log("result of specific user =>", result);
      return res.send(result);
    }
    const result = await getHospitals();
    console.log("result =>", result);
    return res.send(result);
  } catch (error) {
    console.log(error);
  }
}

async function addHospitalLocation(req, res) {
  try {
    const hospital = await Hospital.findByIdAndUpdate(
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
    return res.send({message:"Hospital Location Added"});
  } catch (error) {
    console.log(error);
  }
}

async function getHospitalByUserId(req, res) {
  try {
    if (req.query.id) {
      const id = req.query.id;
      const result = await Hospital.findOne({user: id});
      console.log(result);
      return res.send(result);
    }
  } catch (error) {
    console.log(error);
  }
}

module.exports = {
  add,
  get,
  addHospitalLocation,
  getHospitalByUserId
};
