package com.quickbite.backend.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class VendorByCategoryResponseDto {
    private Long id;
    private String name;
    private String imageUrl;
    private String discountText;
    private Integer priceForOne;
    private Integer deliveryTimeInMinutes;
    private Double rating;
    private List<String> cuisines;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<String> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<String> cuisines) {
        this.cuisines = cuisines;
    }
}
