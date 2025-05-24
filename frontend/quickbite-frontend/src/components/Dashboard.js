import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './Dashboard.css';
import VendorListPage from './VendorListPage';
import api from '../api';

const Dashboard = () => {
  const [categories, setCategories] = useState([]);
  const [productsByCategory, setProductsByCategory] = useState({});
  const [activeCategory, setActiveCategory] = useState('');
  const [featuredProducts, setFeaturedProducts] = useState([]);
  const [userProfile, setUserProfile] = useState(null);
  const [cartData, setCartData] = useState(null);
  const [banners, setBanners] = useState([]);
  const [vendors, setVendors] = useState([]);
  const [selectedVendor, setSelectedVendor] = useState(null);
  const [activeCategoryId, setActiveCategoryId] = useState('');


  useEffect(() => {
    const fetchData = async () => {
      try {
        const [
          categoriesRes,
          productsRes,
          featuredRes,
          userRes,
          cartRes,
          bannersRes
        ] = await Promise.all([
          api.get('/categories/getAll'),
          api.get('/product/getAll'),
          api.get('/product/featured'),
          api.get('/user/profile'),
          api.get('/cart'),
          api.get('/banner/active')
        ]);

        setCategories(categoriesRes.data);
        setFeaturedProducts(featuredRes.data);
        setUserProfile(userRes.data);
        setCartData(cartRes.data);
        setBanners(bannersRes.data);

        // Group products by category name
        const groupedProducts = productsRes.data.reduce((acc, product) => {
          const categoryName = product.category.name;
          if (!acc[categoryName]) {
            acc[categoryName] = [];
          }
          acc[categoryName].push(product);
          return acc;
        }, {});
        setProductsByCategory(groupedProducts);
        setActiveCategory(Object.keys(groupedProducts)[0]);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []);

  return (
    <div className="dashboard">
      {/* Sidebar with Categories */}
      <aside className="sidebar">
        {categories.map((category) => (
          <div
            key={category.id}
            className={`category ${activeCategory === category.name ? 'active' : ''}`}
            onClick={async () => {
                setActiveCategory(category.name);
                setActiveCategoryId(category.id);
                setSelectedVendor(null); // reset vendor on category change

                try {
                    const vendorsRes = await api.get(`/vendors/category/${category.id}`);
                    setVendors(vendorsRes.data);
                } catch (error) {
                    console.error('Error fetching vendors:', error);
                }
                }}
          >
            {category.name} ({productsByCategory[category.name]?.length || 0})
          </div>
        ))}
      </aside>

      {/* Main Content */}
      <main className="main-content">
        {/* Header with User Info and Cart */}
        <div className="header">
          <h2>Order Online</h2>
          <p className="eta">⏱️ 24 min</p>
          {userProfile && (
            <div className="user-info">
              <span>Welcome, {userProfile.name}</span>
            </div>
          )}
          {cartData && (
            <div className="cart-info">
              <span>Cart: {cartData.items.length} items</span>
            </div>
          )}
        </div>

        {/* Banners */}
        {banners.length > 0 && (
          <div className="banners">
            {banners.map((banner) => (
              <div key={banner.id} className="banner">
                <img src={banner.imageUrl} alt={banner.title} />
                <div className="banner-text">
                  <h3>{banner.title}</h3>
                  <p>{banner.description}</p>
                </div>
              </div>
            ))}
          </div>
        )}

        {/* Featured Products */}
        {false && featuredProducts.length > 0 && (
          <section className="featured-section">
            <h3>Featured Items</h3>
            <div className="items-list">
              {featuredProducts.map((item) => (
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
        )}

        {!selectedVendor && vendors.length > 0 && (
        <section className="vendor-section">
            <h3 className="section-title">Best in {activeCategory}</h3>
            <VendorListPage categoryId={activeCategoryId}></VendorListPage>
        </section>
        )}

        {/* Products by Active Category & Vendor */}
        {false && 
        <section className="category-section">
          <h3 className="section-title">Best in {activeCategory}</h3>
          <div className="items-list">
            {productsByCategory[activeCategory]?.map((item) => (
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
        }
    
      </main>
    </div>
  );
};

export default Dashboard;
