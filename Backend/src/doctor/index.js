const { Router } = require("express");
const { addDr, getDoctors, addDrAvailability } = require("./controller");
const router = Router();

router.post("/", addDr);
router.post("/available", addDrAvailability);
router.get("/", getDoctors);

module.exports = router;