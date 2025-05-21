package com.quickbite.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="vendors")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cat_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category category;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String imageUrl;
    private String discountText;
    @Column(name = "price_for_one")
    private Integer priceForOne;
    @Column(name = "delivery_time_in_minutes")
    private Integer deliveryTimeInMinutes;
    @ElementCollection
    @CollectionTable(name = "vendor_cuisines", joinColumns = @JoinColumn(name = "vendor_id"))
    @Column(name = "cuisine")
    private List<String> cuisines;
    private Double rating;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDiscountText() {
        return discountText;
    }

    public void setDiscountText(String discountText) {
        this.discountText = discountText;
    }

    public Integer getPriceForOne() {
        return priceForOne;
    }

    public void setPriceForOne(Integer priceForOne) {
        this.priceForOne = priceForOne;
    }

    public Integer getDeliveryTimeInMinutes() {
        return deliveryTimeInMinutes;
    }

    public void setDeliveryTimeInMinutes(Integer deliveryTimeInMinutes) {
        this.deliveryTimeInMinutes = deliveryTimeInMinutes;
    }

    public List<String> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<String> cuisines) {
        this.cuisines = cuisines;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
