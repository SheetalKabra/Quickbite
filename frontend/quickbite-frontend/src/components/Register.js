import { useState } from "react";
// import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { FaFacebookF, FaGoogle, FaLinkedinIn } from "react-icons/fa";
import "./Register.css"; // Import custom CSS


const Register = () => {
    const [formData, setFormData] = useState({
      firstName: "",
      lastName: "",
      email: "",
      mobile: "",
      password: "",
      role: "USER", // Default role
    });
  
    const navigate = useNavigate();
  
    const handleChange = (e) => {
      setFormData({ ...formData, [e.target.name]: e.target.value });
    };
  
    const handleSubmit = async (e) => {
      e.preventDefault();
    
      console.log(formData.firstName);
      console.log(formData.lastName);
      console.log(formData.email);
      console.log(formData.mobile);
      console.log(formData.password);
      console.log(formData.role);
      try {
        const response = await axios.post(
            "http://localhost:8081/api/v1/auth/register",
            {
                firstName: formData.firstName,
                lastName: formData.lastName,
                email: formData.email,
                mobile: formData.mobile,
                password: formData.password,
                role: formData.role,
            },
            {
              headers: {
                "Content-Type": "application/json",  // 👈 Ensure JSON
              },
            }
          );
        alert(response.data);
        navigate("/"); // Redirect to login page
      } catch (error) {
        console.error("Registration failed", error.response?.data || error.message);
        alert("Registration failed!");
      }
    };
  

  return (
    <div className="register-container">
      <div className="register-box">
        {/* Left Section - Welcome Back */}
        <div className="welcome-box">
          <h2>Welcome Back!</h2>
          <p>To keep connected with us, please login with your personal info</p>
          <button onClick={() => navigate("/")}>Sign In</button>
        </div>

        {/* Right Section - Create Account */}
        <div className="register-form">
          <h2>Create Account</h2>

          {/* Social Login Buttons */}
          <div className="social-login">
            <button className="facebook"><FaFacebookF /></button>
            <button className="google"><FaGoogle /></button>
            <button className="linkedin"><FaLinkedinIn /></button>
          </div>

          {/* <p>or use your email for registration:</p> */}

          {/* Registration Form */}
          <form onSubmit={handleSubmit}>
            <input type="text" name="firstName" placeholder="First Name" value={formData.firstName} onChange={handleChange} required />
            <input type="text" name="lastName" placeholder="Last Name" value={formData.lastName} onChange={handleChange} required />
            <input type="email" name="email" placeholder="Email" value={formData.email} onChange={handleChange}  required />
            <input type="text" name="mobile" placeholder="Mobile" value={formData.mobile} onChange={handleChange} required />  
            <input type="password" name="password" placeholder="Password" value={formData.password} onChange={handleChange} required />
            {/* Dropdown for Role */}
            <div className="dropdown-container">
            <select name="role" required>
                <option value="">-- SELECT ROLE --</option>
                <option value="USER">USER</option>
                <option value="ADMIN">ADMIN</option>
            </select>
            </div>
            <br></br>
            <button type="submit">Sign Up</button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Register;
