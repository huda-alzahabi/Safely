const { Router } = require("express");
const {get, register, login, editProfile, getUsersCount,editUser,removeUser} = require("./controller");
const userMiddleware = require("../../middleware/user_middleware");

const router = Router();

router.get("/auth/get",userMiddleware(), get);
router.post("/auth/register", register);
router.post("/auth/login", login);
router.post("/profile",userMiddleware(), editProfile);
router.get("/count",userMiddleware(), getUsersCount);
router.delete("/auth/delete",userMiddleware(), removeUser);


module.exports = router;