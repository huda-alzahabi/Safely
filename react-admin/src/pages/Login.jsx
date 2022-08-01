import { useState } from "react";
import Button from "../components/Button";
import img from "../assets/login.png";
import TextField from '@material-ui/core/TextField';
import jwt_decode from "jwt-decode";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const nav=useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleEmail = (e) => {
    setEmail(e.target.value);
  };

  const handlePassword = (e) => {
    setPassword(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    login();
    setEmail("");
    setPassword("");
  };

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
    console.log(result.token);
    window.localStorage.setItem("Bearer", result.token);
    var user = jwt_decode(result.token);
    window.localStorage.setItem("user_id", user._id);
    console.log(user);     console.log(result);
     nav("/statistics");
  };

  return (
    <div className="login">
      <div className="container">
        <div className="form">
          <div>
            <h1>Sign in Here</h1>
          </div>
          <form>
            
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
      <div className="login-img">
        <img src={img} alt="login" />
      </div>
    </div>
  );
};
export default Login;
