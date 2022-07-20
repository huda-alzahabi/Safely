const Patient = require("../../model/Patient");

async function addPatient(body) {
    const {
        phone_number,
        date_of_birth,
        nationality,
        sex,
        marital_status,
        user,
    } = body;

    const patient = new Patient({
        phone_number,
        date_of_birth,
        nationality,
        sex,
        marital_status,
        user,
    });

    return await patient.save();
}


module.exports = {
    addPatient,
};