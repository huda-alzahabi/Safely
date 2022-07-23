const { Router } = require("express");
const { add,addLocation,addMedicalRecords,findNearbyHospitals } = require("./controller");
const router = Router();

router.post("/", add);
router.post("/location", addLocation);
router.post("/records", addMedicalRecords);
router.get("/nearby", findNearbyHospitals);

module.exports = router;