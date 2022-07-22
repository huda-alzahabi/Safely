const { Router } = require("express");
const { add, get,addHospitalLocation} = require("./controller");
const router = Router();

router.post("/", add);
router.get("/", get);
router.post("/location", addHospitalLocation);

module.exports = router;