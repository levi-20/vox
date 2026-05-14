export default function LoginPage() {
    function handleSubmit(e) {
        e.preventDefault();
        const formData = new FormData(e.currentTarget);
        const username = formData.get("username");
        const password = formData.get("password");
        console.log("login method called", { username, password });
        //auth API, then navigate
    }

    return (
        <div className="content-center">
            <div className="login-title"><span>Login Now</span></div>
            <div>
                <form onSubmit={handleSubmit}>
                    <label>
                        <span>Username</span>
                        <input className="input-item" name="username" required />
                    </label>
                    <label>
                        <span>Password</span>
                        <input className="input-item" name="password" type="password" required />
                    </label>
                    <button className="btn login-btn" type="submit">Login</button>
                </form>
            </div>
        </div>
    );
}