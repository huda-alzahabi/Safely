const { addHospital, getHospitals, getHospitalById } = require("./service");

async function add(req, res) {
  try {
    const newHospital = await addHospital(req.body);
    console.log("newHospital =>", newHospital);
    return res.status(200).send({ message: "New Hospital Added" });
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

module.exports = {
  add,
  get,
};
