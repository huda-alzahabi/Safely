const { Router } = require("express");
const { add, addHospitalAddress } = require("./controller");
const router = Router();

router.post("/", add);
router.post("/address", addHospitalAddress);

module.exports = router;