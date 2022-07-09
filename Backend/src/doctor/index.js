const { Router } = require("express");
const { addDr, addAvailability, getDoctors } = require("./controller");
const router = Router();

router.post("/", addDr);
router.post("/available", addAvailability);
router.get("/", getDoctors);

module.exports = router;