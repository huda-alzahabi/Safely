const { Router } = require("express");
const { register } = require("./controller");
const router = Router();

router.post("/auth/register", register);

module.exports = router;