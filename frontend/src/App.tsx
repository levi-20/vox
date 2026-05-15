import { BrowserRouter, Route, Routes } from "react-router-dom"
import LoginPage from "./pages/LoginPage"
import Navigation from "./components/navigation"
import { JSX } from "react"

function App(): JSX.Element {
	return (
		<BrowserRouter>
			<Navigation />
			<Routes>
				<Route path="/" element={<h1>Vox</h1>} />
				<Route path="/login" element={<LoginPage />} />
				<Route path="/register" element={<div>Register Page</div>} />
				<Route path="/rooms" element={<div>Rooms Page</div>} />
				<Route path="/rooms/:roomId" element={<div>Chat Page</div>} />
				<Route path="/policies" element={<div>Policy Page</div>} />
				<Route path="/contact-us" element={<div>Contact Us</div>} />
			</Routes>
		</BrowserRouter>
	)
}

export default App