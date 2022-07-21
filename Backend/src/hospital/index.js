const { Router } = require("express");
const { add, get} = require("./controller");
const router = Router();

router.post("/", add);
router.get("/", get);
module.exports = router;