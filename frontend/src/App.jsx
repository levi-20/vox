import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import Navigation from "./components/navigation";

function App() {

  return (
    <BrowserRouter>
      <Navigation />
      <Routes>
        <Route path="/" element={<h1>Vox</h1>} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<div>Register Page</div>} />
        <Route path="/rooms" element={<div>Rooms Page</div>} />
        <Route path="/rooms/:roomId" element={<div>Room Chat Page</div>} />
        <Route path="/policies" element={<div>Policy Page</div>} />
        <Route path="/contact-us" element={<div>Contact us Page</div>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App
