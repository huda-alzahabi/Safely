const { Router } = require("express");
const { addDr, getDoctors } = require("./controller");
const router = Router();

router.post("/", addDr);
router.get("/", getDoctors);

module.exports = router;