import {BrowserRouter, Route, Routes} from "react-router-dom";

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<h1>Vox</h1>}/>
        <Route path="/login" element={<div>Login Page</div>}></Route>
        <Route path="/register" element={<div>Register Page</div>}></Route>
        <Route path="/rooms" element={<div>Rooms Page</div>}></Route>
        <Route path="/rooms/:roomId" element={<div>Room Chat Page</div>}></Route>
        <Route path="/policies" element={<div>Policy Page</div>}></Route>
        <Route path="/contact-us" element={<div>Contact us Page</div>}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App
