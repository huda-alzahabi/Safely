const { Router } = require("express");
const { add,addLocation,addMedicalRecords,findNearbyHospitals,bookAppointment } = require("./controller");
const router = Router();

router.post("/", add);
router.post("/location", addLocation);
router.post("/records", addMedicalRecords);
router.get("/nearby", findNearbyHospitals);
router.get("/book", bookAppointment);


module.exports = router;