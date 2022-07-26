const { Router } = require("express");
const {get, register, login,editProfile} = require("./controller");
const userMiddleware = require("../../middleware/user_middleware");

const router = Router();

router.get("/auth/get",userMiddleware(), get);
router.post("/auth/register", register);
router.post("/auth/login", login);
router.post("/profile",userMiddleware(), editProfile);

module.exports = router;