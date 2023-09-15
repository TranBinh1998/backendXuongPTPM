package com.poly.test.testptpm.service;

import com.poly.test.testptpm.enties.Brand;
import com.poly.test.testptpm.enties.Product;
import com.poly.test.testptpm.enties.ProductBrand;


public interface ProductBrandService {

    void saveProductBrand(ProductBrand productBrand);

    void delAllByIdProduct(Long id);

    ProductBrand findByBrandIdAndProductId(Product productId);
}
