const { Router } = require("express");
const {get, register, login,editProfile} = require("./controller");
const router = Router();
router.get("/auth/get", get);
router.post("/auth/register", register);
router.post("/auth/login", login);
router.post("/profile", editProfile);

module.exports = router;