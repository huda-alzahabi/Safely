const { Router } = require("express");
const { add,addLocation } = require("./controller");
const router = Router();

router.post("/", add);
router.post("/location", addLocation);

module.exports = router;