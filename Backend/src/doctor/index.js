const { Router } = require("express");
const { addDr, getDoctors, addDrAvailability,getAvailabilityByDrId,getTimesByAvailabilityId } = require("./controller");
const router = Router();

router.post("/", addDr);
router.post("/available", addDrAvailability);
router.get("/", getDoctors);
router.get("/available", getAvailabilityByDrId);
router.get("/available/times", getTimesByAvailabilityId);

module.exports = router;