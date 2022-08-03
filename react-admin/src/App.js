import "./App.css";
import Login from "./pages/Login";
import Statistics from "./pages/Statistics";
import Patients from "./pages/Patients";
import Hospitals from "./pages/Hospitals";
import Doctors from "./pages/Doctors";

import { BrowserRouter, Route, Routes } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Routes>
          <Route path="/login" element={<Login />}></Route>
          <Route path="/statistics" element={<Statistics />}></Route>
          <Route path="/patients" element={<Patients />}></Route>
          <Route path="/hospitals" element={<Hospitals />}></Route>
          <Route path="/doctors" element={<Doctors />}></Route>
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
