const UserType = require("../../model/UserType");

function addUserType() {
    var admin = new UserType({
        name: "Admin",
    });
    admin.save();

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