import React from "react";

const Footer = () => {
  return (
    <footer style={styles.footer}>
      <p style={{color: "black"}}>&copy; {new Date().getFullYear()} My App. All rights reserved.</p>
    </footer>
  );
};

const styles = {
  footer: { background: "#6ea63a", padding: "5px", color: "white", textAlign: "center", position: "fixed", bottom: 0, width: "100%" },
};

export default Footer;
