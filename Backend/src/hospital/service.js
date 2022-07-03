const Hospital = require("../../model/Hospital");

async function addHospital(body) {
    const { phone_number, user } = body;

    const hospital = new Hospital({
        phone_number,
        user,
    });

    return await hospital.save();
}

module.exports = {
    addHospital,
};