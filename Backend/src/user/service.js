const User = require("../../model/User");
const jwt = require("jsonwebtoken");
const TOKEN_SECRET = process.env.TOKEN_SECRET || "";

async function addUser(body, hashPassword) {
    const { name, email } = body;

    const user = new User({
        name,
        email,
        password: hashPassword,
    });

    const token = jwt.sign({ _id: user._id, name: user.name, email: user.email },
        TOKEN_SECRET
    );

    user.token = token;

    return await user.save();
}

module.exports = {
    addUser,
};