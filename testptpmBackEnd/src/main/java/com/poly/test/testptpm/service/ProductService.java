package com.poly.test.testptpm.service;

import com.poly.test.testptpm.dto.ProductDTO;
import com.poly.test.testptpm.enties.Product;
import com.poly.test.testptpm.request.ProductFilterRequest;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();

    Product saveProduct(Product product);

    ProductDTO findProductById(long id);

    boolean existsById(Long id);




    void deleteProduct(Product product);

    void deleteById(Long id);

    List<ProductDTO> searchProducts(String query);

    List<ProductDTO> getProductsByProductNameProductPriceProdcutBrandAndCategory(ProductFilterRequest productFilterRequest);



    List<ProductDTO> getAllProductDTO();

    Product getProductById(Long id);



}
