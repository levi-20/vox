import { JSX } from "react";
import { Link } from "react-router-dom";

export default function Navigation(): JSX.Element {
    return (
        <header>
            <nav className="navigation">
                <Link to="/" className="nav-logo">Vox</Link>
                <ul className="nav nav-container">
                    <li className="nav-item"><Link to="/login">Login</Link></li>
                    <li className="nav-item"><Link to="/register">Register</Link></li>
                </ul>
            </nav>
        </header>
    )
}