const { addUserType } = require("./service");

async function add(req, res) {
    try {
        const newUserType = await addUserType();
        return res.status(200).send(newUserType); // 200
    } catch (error) {
        console.log(error);
        res.status(500).send(error);
    }
}
module.exports = {
    add,
};