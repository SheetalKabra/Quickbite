package com.quickbite.backend.controller;

import com.quickbite.backend.dto.BannerResponseDto;
import com.quickbite.backend.dto.CartItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DummyController {

    @GetMapping("/user/profile")
    public ResponseEntity<UserResponseDto> userProfile(){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(1L);
        userResponseDto.setName("John Doe");
        userResponseDto.setEmail("john@example.com");
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/cart")
    public ResponseEntity<CartResponeDto> getCart(){
        CartResponeDto cartResponeDto = new CartResponeDto();
        List<CartItemDto> cartItemDtoList = new ArrayList<>();

        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProductId(101L);
        cartItemDto.setName("Veg Burger");
        cartItemDto.setQuantity(2);
        cartItemDto.setPrice(101.1);
        cartItemDto.setImage("https://example.com/images/veg-burger.jpg");
        cartItemDtoList.add(cartItemDto);

        CartItemDto cartItemDto1 = new CartItemDto();
        cartItemDto1.setProductId(102L);
        cartItemDto1.setName("Cold Coffee");
        cartItemDto1.setQuantity(1);
        cartItemDto1.setPrice(85);
        cartItemDto1.setImage("https://example.com/images/cold-coffee.jpg");
        cartItemDtoList.add(cartItemDto1);

        cartResponeDto.setUserId(1L);
        cartResponeDto.setTotalAmount(283.22);
        cartResponeDto.setItems(cartItemDtoList);

        return ResponseEntity.ok(cartResponeDto);
    }

    @GetMapping("/banner/active")
    public ResponseEntity<List<BannerResponseDto>> getActiveBanner(){
        List<BannerResponseDto> bannerResponseDtoList = new ArrayList<>();
        BannerResponseDto bannerResponseDto = new BannerResponseDto();
        bannerResponseDto.setId(1L);
        bannerResponseDto.setTitle("Summer Combo Offer");
        bannerResponseDto.setDescription("Get 2 burgers and 1 drink at just ₹199");
        bannerResponseDto.setImageUrl("https://example.com/images/summer-combo.jpg");
        bannerResponseDtoList.add(bannerResponseDto);

        BannerResponseDto bannerResponseDto1 = new BannerResponseDto();
        bannerResponseDto1.setId(2L);
        bannerResponseDto1.setTitle("Flat 30% Off");
        bannerResponseDto1.setDescription("On all dessert items this weekend");
        bannerResponseDto1.setImageUrl("https://example.com/images/dessert-offer.jpg");
        bannerResponseDtoList.add(bannerResponseDto1);

        return ResponseEntity.ok(bannerResponseDtoList);
    }
}
