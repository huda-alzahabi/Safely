require("dotenv").config();
const express = require("express");
const mongoose = require("mongoose");
const cors = require("cors");

const userRouter = require("./src/user");

const DB_CONNECT = process.env.DB_CONNECT || "";
mongoose.connect(DB_CONNECT, (err) => {
    if (err) console.log(err);
    else console.log("mongdb is connected");
});

const app = express();
app.use(cors());
app.use(express.json());
app.use("/api/user", userRouter);

app.listen(3030, () => console.log("Server running"));