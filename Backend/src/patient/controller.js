const { addPatient } = require("./service");

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
module.exports = {
    add,
};