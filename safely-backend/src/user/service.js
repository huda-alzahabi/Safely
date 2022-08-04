const User = require("../../model/User");
const jwt = require("jsonwebtoken");
const TOKEN_SECRET = process.env.TOKEN_SECRET || "";

async function getUsers() {
    return await User.find();
}

async function getById(id) {
    return await User.findById(id);
}

async function addUser(body, hashPassword) {
    const { name, email,userType } = body;

    const user = new User({
        name,
        email,
        password: hashPassword,
        userType
    });

    const token = jwt.sign({ _id: user._id, name: user.name, email: user.email },
        TOKEN_SECRET
    );

    user.token = token;

    return await user.save();
}

async function getByEmail(email) {
    return await User.findOne({
        email,
    });
}

module.exports = {
    getUsers,
    getById,
    addUser,
    getByEmail,
};