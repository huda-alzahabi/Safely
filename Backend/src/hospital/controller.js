const { addHospital } = require("./service");

async function add(req, res) {
  try {
    const newHospital = await addHospital(req.body);
    console.log("newHospital =>", newHospital);
    return res.status(200).send(newHospital);
  } catch (error) {
    console.log(error);
    res.status(500).send(error);
  }
}

module.exports = {
  add,
};
