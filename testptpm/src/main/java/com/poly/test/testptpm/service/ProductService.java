package com.poly.test.testptpm.service;

import com.poly.test.testptpm.dto.ProductDTO;
import com.poly.test.testptpm.enties.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();

    void saveProduct(Product product);

    ProductDTO findProductById(long id);

    boolean existsById(Long id);




    void deleteProduct(Product product);

    void deleteById(Long id);

    List<Product> searchProducts(String query);

    List<ProductDTO> findLaptopsByBrandAndCategory(boolean isEmptyBrands, boolean isEmptyCategory, List<Long> brands, List<Long> category);
}
