package com.quickbite.backend.controller;

import com.quickbite.backend.dto.BannerResponseDto;
import com.quickbite.backend.model.Banner;
import com.quickbite.backend.service.BannerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/banner")
public class BannerController {
    private final BannerService bannerService;

    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @PostMapping("/add")
    public ResponseEntity<Banner> addBanner(@RequestBody Banner banner){
        Banner savedBanner = bannerService.addBanner(banner);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBanner);
    }

    @GetMapping("/active")
    public List<BannerResponseDto> getActiveBanners() {
        return bannerService.getActiveBanners();
    }
}
