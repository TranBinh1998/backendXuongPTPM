package com.poly.test.testptpm.service.impl;

import com.poly.test.testptpm.repository.ProductBrandRepository;
import com.poly.test.testptpm.enties.Product;
import com.poly.test.testptpm.enties.ProductBrand;
import com.poly.test.testptpm.service.ProductBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductBrandServiceImpl implements ProductBrandService {

    @Autowired
    private  ProductBrandRepository productBrandRepository;

    @Override
    public void saveProductBrand(ProductBrand productBrand) {
        productBrandRepository.saveAndFlush(productBrand);
    }

    @Override
    public void delAllByIdProduct(Long id) {
        productBrandRepository.deleteAllByProductId(id);
    }

    @Override
    public ProductBrand findByBrandIdAndProductId(Product productId) {
        return productBrandRepository.findProductBrandByBrandIdAndProductId(productId.getId());
    }
}
