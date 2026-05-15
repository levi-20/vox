import { useState } from 'react'
import { useNavigate } from 'react-router-dom'

export default function LoginPage(): JSX.Element {
    const [error, setError] = useState<string | null>(null)
    const [loading, setLoading] = useState<boolean>(false)
    const navigate = useNavigate()

    async function handleSubmit(e: React.FormEvent<HTMLFormElement>): Promise<void> {
        e.preventDefault()
        setError(null)
        setLoading(true)

        const formData = new FormData(e.currentTarget)
        const username = formData.get('username') as string
        const password = formData.get('password') as string

        console.log('login called', { username, password })
        // API call goes here
    }

    return (
        <div className="container">
            <div className="login-box">
                <div className="login-title">Login Now</div>
                <div className="login-form">
                    <form onSubmit={handleSubmit}>
                        <div className="login-item">
                            <label>
                                <span className="input-label">Username</span>
                                <input
                                    className="input-item username"
                                    required
                                    name="username"
                                />
                            </label>
                        </div>
                        <div className="login-item">
                            <label>
                                <span className="input-label">Password</span>
                                <input
                                    className="input-item password"
                                    type="password"
                                    required
                                    name="password"
                                />
                            </label>
                        </div>

                        {error && (
                            <div className="login-item error-message">
                                {error}
                            </div>
                        )}

                        <div className="login-item">
                            <button
                                className="btn login-btn"
                                type="submit"
                                disabled={loading}
                            >
                                {loading ? 'Logging in...' : 'Login'}
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    )
}