package com.quickbite.backend.service;

import com.quickbite.backend.dto.BannerResponseDto;
import com.quickbite.backend.model.Banner;
import com.quickbite.backend.repository.BannerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService{
    private final BannerRepository bannerRepository;

    public BannerServiceImpl(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    @Override
    public Banner addBanner(Banner banner) {
        return bannerRepository.save(banner);
    }

    public List<BannerResponseDto> getActiveBanners() {
        List<Banner> bannerList = bannerRepository.findByActiveTrue();
        List<BannerResponseDto> bannerResponseDtoList = new ArrayList<>();
        for(Banner banner: bannerList){
            BannerResponseDto bannerResponseDto = new BannerResponseDto();
            bannerResponseDto.setId(banner.getId());
            bannerResponseDto.setTitle(banner.getTitle());
            bannerResponseDto.setDescription(banner.getDescription());
            bannerResponseDto.setImageUrl("http://localhost:8081/backend/uploads/images/banners/"+banner.getImage());
            bannerResponseDtoList.add(bannerResponseDto);
        }
        return bannerResponseDtoList;
    }
}
