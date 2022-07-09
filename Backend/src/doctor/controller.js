const { addDoctor } = require("./service");
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


module.exports = {
    addDr,

};