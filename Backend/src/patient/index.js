const { Router } = require("express");
const { add,addLocation,addMedicalRecords } = require("./controller");
const router = Router();

router.post("/", add);
router.post("/location", addLocation);
router.post("/records", addMedicalRecords);


module.exports = router;