const { Router } = require("express");
const { addDr, getDoctorsCount, addDrAvailability,getAvailabilityByDrId,getTimesByAvailabilityId,getAppointmentsByDrId,getDoctorByUserId,getDoctorsPerHospital,get } = require("./controller");
const router = Router();
const userMiddleware = require("../../middleware/user_middleware");


router.post("/",userMiddleware(), addDr);
router.post("/available",userMiddleware(), addDrAvailability);
router.get("/",userMiddleware(), getDoctorsPerHospital);
router.get("/available",userMiddleware(), getAvailabilityByDrId);
router.get("/available/times",userMiddleware(), getTimesByAvailabilityId);
router.get("/appointment",userMiddleware(), getAppointmentsByDrId);
router.get("/user",userMiddleware(), getDoctorByUserId);
router.get("/all",userMiddleware(), get);
router.get("/count",userMiddleware(), getDoctorsCount);




module.exports = router;