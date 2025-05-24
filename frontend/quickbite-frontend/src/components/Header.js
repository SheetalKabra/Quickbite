import React, { useContext, useState } from "react";
import { Link } from "react-router-dom";
import { FaUserCircle } from "react-icons/fa";
import "./Header.css"; // Custom CSS for styling
import { AuthContext } from "./AuthContext";

const Header = ({cartCount}) => {
    const { token, logout } = useContext(AuthContext);
    const [isOpen, setIsOpen] = useState(false);
    const handleToggle = () => setIsOpen(!isOpen);
    const handleLogout = () => {
        localStorage.removeItem("token");
        window.location.href="/login";
    };
    // const token = localStorage.getItem('token');
  return (
    <header className="header">
      <h1 className="header-heading">QuickBite</h1>
    {token &&
        <nav>
            <ul style={styles.navList}>
            <li><Link style={styles.listStyle} to="/home">Home</Link></li>
            <li><Link style={styles.listStyle} to="/order-history">My Orders</Link></li>
            <li><Link style={styles.listStyle} to="/about">About</Link></li>
            <li><Link style={styles.listStyle} to="/contact">Contact</Link></li>
            <li><Link style={styles.listStyle} to="/cart">
                        Cart
                        {cartCount > 0 && (
                            <span style={{
                                top: "-8px",
                                right: "-8px",
                                background: "red",
                                color: "white",
                                borderRadius: "50%",
                                padding: "2px 6px",
                                margin: "2px",
                                fontSize: "12px"
                            }}>
                                {cartCount}
                            </span>
                        )}
            
            </Link></li>
            <li><Link style={styles.listStyle} to="/" onClick={logout}>Logout</Link></li>

            </ul>
        </nav>
    }
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
