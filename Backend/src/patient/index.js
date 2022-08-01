const { Router } = require("express");
const { add,addLocation,addMedicalRecords,findNearbyHospitals,bookAppointment,getAppointmentsByPatientId,getPatientByUserId,get } = require("./controller");
const router = Router();
const userMiddleware = require("../../middleware/user_middleware");


router.post("/",userMiddleware(), add);
router.post("/location",userMiddleware(), addLocation);
router.post("/records",userMiddleware(), addMedicalRecords);
router.get("/nearby",userMiddleware(), findNearbyHospitals);
router.post("/book",userMiddleware(), bookAppointment);
router.get("/appointment",userMiddleware(), getAppointmentsByPatientId);
router.get("/user",userMiddleware(), getPatientByUserId);
router.get("/get",userMiddleware(), get);




module.exports = router;