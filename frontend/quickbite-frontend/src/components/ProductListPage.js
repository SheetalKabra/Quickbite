import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './Dashboard.css';
import { useNavigate, useParams } from 'react-router-dom';

export default function ProductListPage() {
  const { categoryId, vendorId } = useParams();
  const [products, setProducts] = useState([]);
  const [vendors, setVendors] = useState([]);
  const [categoryName, setCategoryName] = useState([]);
//   const navigate = useNavigate();
  const [activeVendorId, setActiveVendorId] = useState(vendorId);
  const [activeVendorName, setActiveVendorName] = useState('');
  const [selectedVendor, setSelectedVendor] = useState(null);

  useEffect(() => {
    
    const fetchData = async () => {
    
    try {
        const [
          products,
          vendors,
          vendorById
        ] = await Promise.all([
          axios.get(`http://localhost:8081/api/v1/product/get/vendor/${vendorId}`),
          axios.get(`http://localhost:8081/api/v1/vendors/category/${categoryId}`),
          axios.get(`http://localhost:8081/api/v1/vendors/get/${vendorId}`),
        ]);
        setProducts(products.data);
        setCategoryName(products.data[0].category.name);
        setVendors(vendors.data);
        setActiveVendorName(vendorById.data.name);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    if (vendorId) {
      fetchData();
    }
  }, [vendorId]);


  return (
    <div className='productDashboard'>
        {/* Sidebar with Vendors */}
      <aside className="sidebar">
        {vendors.map((vendor) => (
          <div
            key={vendor.id}
            className={`vendor ${activeVendorName === vendor.name ? 'active' : ''}`}
            onClick={async () => {
                setActiveVendorName(vendor.name);
                setActiveVendorId(vendor.id);
                setSelectedVendor(null); // reset vendor on vendor change

                try {
                    const vendorsRes = await axios.get(`http://localhost:8081/api/v1/product/get/vendor/${vendor.id}`);
                    setProducts(vendorsRes.data);
                    setCategoryName(vendorsRes.data[0].category.name);
                } catch (error) {
                    console.error('Error fetching vendors:', error);
                }
                }}
          >
            {vendor.name}
          </div>
        ))}
      </aside>
    
    <section className="category-section"  style={{marginLeft:"10%", paddingLeft:"10%"}}>
          <h3 className="section-title">Best in {categoryName}</h3>
          <div className="items-list">
            {products.map((item) => (
              <div className="item-card" key={item.id}>
                <div className="item-info">
                  <p className={`veg-icon ${item.vegOrNonVeg === 'VEG' ? 'veg' : 'non-veg'}`}>
                        {item.vegOrNonVeg === 'VEG' ? '🟢' : '🔴'}
                    </p>
                  <h4>{item.name}</h4>
                  <p className="price">₹{item.price}</p>
                  <p className="desc">{item.description}</p>
                </div>
                {item.image && (
                  <div className="item-img">
                    <img src={item.image} alt={item.name} />
                  </div>
                )}
                <button  className="add-button">Add</button>
              </div>
            ))}
          </div>
        </section>
    </div>
  );
}
