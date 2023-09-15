package com.poly.test.testptpm.service;


import com.poly.test.testptpm.enties.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandsService {
    List<Brand> getlistBrands();

    Brand getBrandById(Long id);

}
