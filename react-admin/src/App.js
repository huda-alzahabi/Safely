import "./App.css";
import Login from "./pages/Login";
import Statistics from "./pages/Statistics";



import { BrowserRouter, Route, Routes } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Routes>
          <Route path="/login" element={<Login />}></Route>
          <Route path="/statistics" element={<Statistics />}></Route>
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
