const { Router } = require("express");
const { addDr, getDoctors, addDrAvailability,getAvailabilityByDrId,getTimesByAvailabilityId } = require("./controller");
const router = Router();
const userMiddleware = require("../../middleware/user_middleware");


router.post("/",userMiddleware(), addDr);
router.post("/available",userMiddleware(), addDrAvailability);
router.get("/",userMiddleware(), getDoctors);
router.get("/available",userMiddleware(), getAvailabilityByDrId);
router.get("/available/times",userMiddleware(), getTimesByAvailabilityId);

module.exports = router;