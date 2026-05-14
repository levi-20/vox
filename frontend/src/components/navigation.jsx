import { Link } from "react-router-dom";

export default function Navigation() {
	return (
		<nav className="navigation">

			<a href="/" className="nav-logo">Vox</a>

			<ul className="nav nav-container">
				<li className="nav-item"><Link to="/login">Login</Link></li>
				<li className="nav-item"><Link to="/register">Register</Link></li>
			</ul>
		</nav>
	)
}

