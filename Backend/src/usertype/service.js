const UserType = require("../../model/UserType");

function addUserType() {
    var doctor = new UserType({
        name: "Doctor",
    });
    doctor.save();

    var patient = new UserType({
        name: "Patient",
    });
    patient.save();

    var hospital = new UserType({
        name: "Hospital",
    });
    hospital.save();
}
module.exports = {
    addUserType,
};