const { Router } = require("express");
const { add, get,addHospitalLocation} = require("./controller");
const userMiddleware = require("../../middleware/user_middleware");

const router = Router();

router.post("/",userMiddleware(), add);
router.get("/", userMiddleware(),get);
router.post("/location",userMiddleware(), addHospitalLocation);

module.exports = router;