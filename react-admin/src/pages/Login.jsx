import { useState } from "react";
import Button from "../components/Button";
import img from "../assets/login.png";
import TextField from '@material-ui/core/TextField';
const Login = () => {
  // States for login
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  // Handling the email change
  const handleEmail = (e) => {
    setEmail(e.target.value);
  };

  // Handling the password change
  const handlePassword = (e) => {
    setPassword(e.target.value);
  };

  // Handling the form submission
  const handleSubmit = (e) => {
    e.preventDefault();
    login();
    setEmail("");
    setPassword("");
  };

  //Sending the user to the users table in the db
  const login = async () => {
    let _data = {
      email: email,
      password: password,
    };

    const res = await fetch("http://127.0.0.1:3030/api/user/auth/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(_data),
    });
    const result = await res.json();
    console.log(result);
    // nav("/Contacts");
  };

  return (
    <div className="login">
      <div className="container">
        <div className="form">
          <div>
            <h1>Sign in Here</h1>
          </div>
          <form>
            {/* Labels and inputs for form data */}

            <TextField label="Email" variant="outlined"
              onChange={handleEmail}
              className="input"
              value={email}
              type="email"
            />

            <TextField label="Password" variant="outlined" 
              onChange={handlePassword}
              className="input"
              value={password}
              placeholder={"Password"}
              type="password"
            />

            <div className="loginbtn">
              <Button color={"#0BA3D3"} text={"Login"} onClick={handleSubmit} />
            </div>
          </form>
        </div>
      </div>
      <div>
        <img src={img} alt="login" />
      </div>
    </div>
  );
};
export default Login;
