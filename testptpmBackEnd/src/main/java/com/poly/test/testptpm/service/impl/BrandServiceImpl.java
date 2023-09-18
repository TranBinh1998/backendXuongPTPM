package com.poly.test.testptpm.service.impl;

import com.poly.test.testptpm.repository.BrandRepository;
import com.poly.test.testptpm.enties.Brand;
import com.poly.test.testptpm.service.BrandsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandsService {


    private BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> getlistBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getBrandById(Long id) {
        Optional<Brand> brand = brandRepository.findById(id);
        return brand.orElse(null);
    }

}
