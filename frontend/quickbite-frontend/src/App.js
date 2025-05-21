import './App.css';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Register from './components/Register';
import Login from './components/Login';
import Header from './components/Header';
import Footer from './components/Footer';
import Dashboard from './components/Dashboard';
import VendorListPage from './components/VendorListPage';
import ProductListPage from './components/ProductListPage';


function App() {
    return (
        <Router>
        <Header></Header>
        <div style={{ minHeight: "80vh", paddingTop: "70px", paddingBottom: "50px" }}>
        <Routes>
            <Route path="/register" element={<Register />} />
            <Route path="/" element={<Login />} />
            <Route path="/home" element={<Dashboard />} />
            <Route path="/vendor" element={<VendorListPage />} />
            <Route path="/product/vendor/:categoryId/:vendorId" element={<ProductListPage />} />
        </Routes>
        </div>
    <Footer />
    </Router>
    );
}

export default App;
