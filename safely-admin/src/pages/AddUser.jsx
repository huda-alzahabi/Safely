import { useState } from "react";
import toast from "react-hot-toast";
import SideNav from "../components/SideNav";
function AddUser() {
  // Initialize Input State
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [userType, setUserType] = useState("");

  const Add = async () => {
    try {
      const res = await fetch("http://127.0.0.1:3030/api/user/auth/register", {
        method: "POST",
        headers: {
          token: localStorage.getItem("Bearer"),
          "content-type": "application/json",
        },
        body: JSON.stringify({
          name: name,
          email: email,
          password: password,
          userType: userType,
        }),
      });
      toast.success("User Added Successfully");
    } catch (err) {
      toast.error("User Already Exists");
    }
  };

  return (
    <>
      {" "}
      <SideNav />
      <div className="add">
        <h6 className="title">Add User</h6>
        <div className="inputBox">
          <label className="logColor">Name</label>
          <input
            type="text"
            value={name}
            onInput={(e) => setName(e.target.value)}
          />

          <br />
          <label className="logColor">Email</label>
          <input
            type="text"
            value={email}
            onInput={(e) => setEmail(e.target.value)}
          />
          <br />
          <label className="logColor">Password</label>
          <input
            type="password"
            value={password}
            onInput={(e) => setPassword(e.target.value)}
          />

          <br />
          <label className="logColor">User Type</label>
          <input
            type="text"
            value={userType}
            onInput={(e) => setUserType(e.target.value)}
          />
          <br />
          <button
            className="submit-btn"
            onClick={() => {
              Add();
            }}
          >
            Submit
          </button>
        </div>
      </div>
    </>
  );
}
export default AddUser;
