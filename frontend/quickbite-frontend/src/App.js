import './App.css';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Register from './components/Register';
import Login from './components/Login';


function App() {
    return (
        <Router>
        <div style={{ minHeight: "80vh", paddingTop: "70px", paddingBottom: "50px" }}>
        <Routes>
            <Route path="/register" element={<Register />} />
            <Route path="/" element={<Login />} />
        </Routes>
        </div>
    {/* <Footer /> */}
    </Router>
    );
}

export default App;
