const { Router } = require("express");
const { addDr, getDoctors, addDrAvailability,getAvailabilityByDrId,getTimesByAvailabilityId,getAppointmentsByDrId,getDoctorByUserId } = require("./controller");
const router = Router();
const userMiddleware = require("../../middleware/user_middleware");


router.post("/",userMiddleware(), addDr);
router.post("/available",userMiddleware(), addDrAvailability);
router.get("/",userMiddleware(), getDoctors);
router.get("/available",userMiddleware(), getAvailabilityByDrId);
router.get("/available/times",userMiddleware(), getTimesByAvailabilityId);
router.get("/appointment",userMiddleware(), getAppointmentsByDrId);
router.get("/user",userMiddleware(), getDoctorByUserId);


module.exports = router;