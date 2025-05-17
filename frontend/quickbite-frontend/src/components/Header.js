import React, { useState } from "react";
import { Link } from "react-router-dom";
import { FaUserCircle } from "react-icons/fa";
import "./Header.css"; // Custom CSS for styling

const Header = ({cartCount}) => {
    const [isOpen, setIsOpen] = useState(false);
    const handleToggle = () => setIsOpen(!isOpen);
    const handleLogout = () => {
        localStorage.removeItem("token");
        window.location.href="/login";
    };
  return (
    <header className="header">
      <h1 className="header-heading">QuickBite</h1>
      <nav>
        <ul style={styles.navList}>
          <li><Link style={styles.listStyle} to="/dashboard">Home</Link></li>
          <li><Link style={styles.listStyle} to="/order-history">My Orders</Link></li>
          <li><Link style={styles.listStyle} to="/about">About</Link></li>
          <li><Link style={styles.listStyle} to="/contact">Contact</Link></li>
          <li><Link to="/cart"style={{ position: "relative", textDecoration: "none", color: "white" }}>
                    🛒
                    {cartCount > 0 && (
                        <span style={{
                            position: "absolute",
                            top: "-8px",
                            right: "-8px",
                            background: "red",
                            color: "white",
                            borderRadius: "50%",
                            padding: "2px 6px",
                            fontSize: "12px"
                        }}>
                            {cartCount}
                        </span>
                    )}
          
          </Link></li>
          <li><Link style={styles.listStyle} to="/"
          onClick={() => {
            localStorage.removeItem("token"); // ❌ Remove token
            window.location.href = "/"; // 🔀 Redirect to login
        }}
        >Logout</Link></li>

        </ul>
      </nav>
    </header>
  );
};

const styles = {
//   header: { background: "#333", 
//     padding: "10px", 
//     color: "white", 
//     display: "flex", 
//     justifyContent: "space-between", 
//     position: "fixed" ,
//     top: "0",
//     left: "0",
//     width: "100%",
//     height: "60px", /* Adjust as needed */
//     color: "white",
//     // align-items: "center",
//     // justify-content: "space-between",
//     padding: "0 20px",
//     // z-index: "1000", /* Ensure it stays above other content */
//     // box-shadow: "0 2px 8px rgba(0, 0, 0, 0.2)",

// },
  navList: { display: "flex", listStyle: "none", gap: "20px" },
  listStyle: {color: "black"},
};

export default Header;
