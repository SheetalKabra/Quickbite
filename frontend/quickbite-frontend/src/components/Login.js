import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { FaFacebookF, FaGoogle, FaLinkedinIn } from "react-icons/fa";
import "./Login.css";  // Importing custom CSS
import { useState } from "react";

const Login = () => {
  const { register, handleSubmit } = useForm();
  const navigate = useNavigate();
  const [loginMethod, setLoginMethod] = useState("EMAIL");

  const onSubmit = async (data) => {
    try {
      const dataWithLoginMethod = { ...data, loginBy: loginMethod };
      const response = await axios.post("http://localhost:8081/api/v1/auth/login", dataWithLoginMethod);
      localStorage.setItem("token", response.data.token);
      console.log("token:"+response.data.token);
      navigate("/dashboard");
    } catch (error) {
      console.error("Login failed", error);
    }
  };
  
  const handleMobileLogin = () => {
    setLoginMethod('MOBILE'); // Set login method to MOBILE
  };

  return (
    <div className="login-container">
      <div className="login-box">
        {/* Left Section - Login Form */}
        <div className="login-form">
          <h2>Login to Your Account</h2>
          <p>Login using social networks</p>

          {/* Social Login Buttons */}
          <div className="social-login">
            <button className="facebook"><FaFacebookF /></button>
            <button className="google"><FaGoogle /></button>
            <button className="linkedin"><FaLinkedinIn /></button>
          </div>

          <div className="divider"></div>

          {/* Login Form */}
          <form onSubmit={handleSubmit(onSubmit)}>
            {loginMethod === "MOBILE" ? (<input {...register("mobile")} type="text" placeholder="Mobile" required />) : <input {...register("email")} type="text" placeholder="Email" required /> }
            <input {...register("password")} type="password" placeholder="Password" required />
            <button type="submit">Sign In</button>
            <br />
            <p style={{ marginLeft: "50%" }}> OR </p> 
            <button onClick={handleMobileLogin} type="button">Login using Mobile</button>
          </form>
        </div>

        {/* Right Section - Sign Up Prompt */}
        <div className="signup-box">
          <h2>New Here?</h2>
          <p>Sign up and discover a great amount of new opportunities!</p>
          <button onClick={() => navigate("/register")}>Sign Up</button>
        </div>
      </div>
    </div>
  );
};

export default Login;
