const { Router } = require("express");
const { addDr, addAvailability } = require("./controller");
const router = Router();

router.post("/", addDr);
router.post("/available", addAvailability);


module.exports = router;