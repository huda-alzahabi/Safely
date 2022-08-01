import "./App.css";
import Login from "./pages/Login";
import PieChart from "./components/PieChart";
import { BrowserRouter, Route, Routes } from "react-router-dom";


function App() {
  return (
    <BrowserRouter>
        <div className="App">

    <Routes>
     <Route path="/login" element={<Login/>}></Route>
     <Route path="/statistics" element={<PieChart/>}></Route>
      
      </Routes>
      </div>
      </BrowserRouter>
  );
}

export default App;
