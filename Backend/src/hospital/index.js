const { Router } = require("express");
const { add, get,addHospitalLocation, getHospitalByUserId} = require("./controller");
const userMiddleware = require("../../middleware/user_middleware");


const router = Router();

router.post("/",userMiddleware(), add);
router.get("/", userMiddleware(),get);
router.post("/location",userMiddleware(), addHospitalLocation);
router.get("/user",userMiddleware(), getHospitalByUserId);

module.exports = router;