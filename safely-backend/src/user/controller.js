const { getUsers, getById, addUser, getByEmail } = require("./service");
const bcrypt = require("bcryptjs");
const jwt = require("jsonwebtoken");
const User = require("../../model/User");

const TOKEN_SECRET = process.env.TOKEN_SECRET || "";

async function get(req, res) {
  try {
    console.log(req.query);

    if (req.query.id) {
      const id = req.query.id;
      const result = await getById(id);
      console.log("result of specific user =>", result);
      return res.send(result);
    }
    const result = await getUsers();

    const resultSorted = result.sort((a, b) => {
      if (a.userType < b.userType) {
        return -1;
      }
      if (a.userType > b.userType) {
        return 1;
      }
      return 0;
    });
    return res.send(resultSorted);
  } catch (error) {
    console.log(error);
  }
}

async function register(req, res) {
  try {
    console.log(req.body);

    const salt = await bcrypt.genSalt(10);
    const hashPassword = await bcrypt.hash(req.body.password, salt);

    const addUserResult = await addUser(req.body, hashPassword);
    console.log("addUserResult =>", addUserResult);

    return res.send({ user: addUserResult._id });
  } catch (error) {
    console.log(error);
  }
}

async function login(req, res) {
  try {
    const user = await getByEmail(req.body.email);
    if (!user) return res.status(400).send("invalid credentials");

    const validPassword = await bcrypt.compare(
      req.body.password,
      user.password
    );
    if (!validPassword) return res.status(400).send("invalid credentials");

    const token = jwt.sign(
      {
        _id: user._id,
        name: user.name,
        email: user.email,
        userType: user.userType,
      },
      TOKEN_SECRET
    );

    return res.status(200).json({ token: token });
  } catch (error) {
    console.log(error);
    res.status(500).send(error);
  }
}
async function editProfile(req, res) {
  try {
    const salt = await bcrypt.genSalt(10);
    const hashPassword = await bcrypt.hash(req.body.password, salt);

    const user = await User.findByIdAndUpdate(
      { _id: req.query.id },
      {
        $set: {
          name: req.body.name,
          email: req.body.email,
          password: hashPassword,
        },
      }
    );
    return res.send({ message: "Contact Successfully Updated" });
  } catch (error) {
    console.log(error);
  }
}

async function getUsersCount(req, res) {
  try {
    console.log(req.query);
    if (req.query.id) {
      const id = req.query.id;
      const result = await getById(id);
      console.log("result of specific user =>", result);
      return res.send(result);
    }

    const result = await getUsers();
    console.log("result =>", result.length);
    return res.send({ users: result.length });
  } catch (error) {
    console.log(error);
  }
}
async function adminLogin(req, res) {
  try {
    const user = await getByEmail(req.body.email);
    if (user.userType === "admin") {
      if (!user) return res.status(400).send("invalid credentials");

      const validPassword = await bcrypt.compare(
        req.body.password,
        user.password
      );
      if (!validPassword) return res.status(400).send("invalid credentials");

      const token = jwt.sign(
        {
          _id: user._id,
          name: user.name,
          email: user.email,
          userType: user.userType,
        },
        TOKEN_SECRET
      );
      return res.status(200).json({ token: token });
    } else {
      return res.status(400).send("Unauthorized");
    }
  } catch (error) {
    console.log(error);
    res.status(500).send(error);
  }
}
async function removeUser(req, res) {
  try {
    const user = await User.findOne({ _id: req.query.id });
    if (!user) console.log(404);
    const deleteResult = await user.remove();
    return res.send("user removed successfully");
  } catch (error) {
    console.log(error);
  }
}

module.exports = {
  get,
  register,
  login,
  editProfile,
  getUsersCount,
  removeUser,
  adminLogin,
};
