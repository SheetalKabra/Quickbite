package com.quickbite.backend.service;

import com.quickbite.backend.dto.BannerResponseDto;
import com.quickbite.backend.model.Banner;

import java.util.List;

public interface BannerService {
    Banner addBanner(Banner banner);
    List<BannerResponseDto> getActiveBanners();
}
