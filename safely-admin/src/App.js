import "./App.css";
import Login from "./pages/Login";
import Statistics from "./pages/Statistics";
import Patients from "./pages/Patients";
import Hospitals from "./pages/Hospitals";
import Doctors from "./pages/Doctors";
import AllUsers from "./pages/AllUsers";
import AddUser from "./pages/AddUser";
import { Toaster } from "react-hot-toast";


import { BrowserRouter, Route, Routes } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
    <Toaster position="top-center" reverseOrder={false} />
      <div className="App">
        <Routes>
          <Route path="/" element={<Login />}></Route>
          <Route path="/statistics" element={<Statistics />}></Route>
          <Route path="/patients" element={<Patients />}></Route>
          <Route path="/hospitals" element={<Hospitals />}></Route>
          <Route path="/doctors" element={<Doctors />}></Route>
          <Route path="/users" element={<AllUsers />}></Route>
          <Route path="/add" element={<AddUser />}></Route>
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
