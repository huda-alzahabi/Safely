const { Router } = require("express");
const { addDr, getDoctors, addDrAvailability,getAvailabilityByDrId } = require("./controller");
const router = Router();

router.post("/", addDr);
router.post("/available", addDrAvailability);
router.get("/", getDoctors);
router.get("/get", getAvailabilityByDrId);

module.exports = router;