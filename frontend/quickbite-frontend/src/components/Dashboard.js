import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './Dashboard1.css';

const Dashboard = () => {
  const [categories, setCategories] = useState([]);
  const [productsByCategory, setProductsByCategory] = useState({});
  const [activeCategory, setActiveCategory] = useState('');
  const [featuredProducts, setFeaturedProducts] = useState([]);
  const [userProfile, setUserProfile] = useState(null);
  const [cartData, setCartData] = useState(null);
  const [banners, setBanners] = useState([]);

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
          axios.get('http://localhost:8081/api/v1/categories/getAll'),
          axios.get('http://localhost:8081/api/v1/product/getAll'),
          axios.get('http://localhost:8081/api/v1/product/featured'),
          axios.get('http://localhost:8081/api/v1/user/profile'),
          axios.get('http://localhost:8081/api/v1/cart'),
          axios.get('http://localhost:8081/api/v1/banner/active')
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
            onClick={() => setActiveCategory(category.name)}
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
        {featuredProducts.length > 0 && (
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

        {/* Products by Active Category */}
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
      </main>
    </div>
  );
};

export default Dashboard;
