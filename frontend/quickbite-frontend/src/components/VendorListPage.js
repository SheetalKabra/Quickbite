import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './VendorListPage.css';
import { useNavigate } from 'react-router-dom';
import api from '../api';

export default function VendorListPage({ categoryId }) {
  const [vendors, setVendors] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchVendors = async () => {
      try {
        const response = await api.get(`/vendors/category/${categoryId}`);
        setVendors(response.data);
      } catch (error) {
        console.error('Error fetching vendors:', error);
      }
    };

    if (categoryId) {
      fetchVendors();
    }
  }, [categoryId]);

  const handleVendorClick = (vendorId) => {
    navigate(`/product/vendor/${categoryId}/${vendorId}`);
  };

  return (
    <div className="vendor-container">
      <h2>Vendors in Selected Category</h2>
      <div className="vendor-grid">
        {vendors.map((vendor) => (
          <div
            key={vendor.id}
            className="vendor-card"
            onClick={() => handleVendorClick(vendor.id)}
          >
            <div className="image-wrapper">
              <img src={vendor.imageUrl} alt={vendor.name} />
              {vendor.discountText && <span className="offer-tag">{vendor.discountText}</span>}
            </div>
            <div className="vendor-info">
              <div className="vendor-header">
                <h3>{vendor.name}</h3>
                <span className="rating">{vendor.rating}★</span>
              </div>
              <p className="cuisine">{vendor.cuisines.join(', ')}</p>
              <div className="vendor-meta">
                <span>{vendor.cost}</span>
                <span>⏱️ {vendor.deliveryTimeInMinutes} mins</span>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
