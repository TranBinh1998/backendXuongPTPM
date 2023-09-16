package com.poly.test.testptpm.service;

import com.poly.test.testptpm.response.ProductResponse;
import com.poly.test.testptpm.enties.Product;
import com.poly.test.testptpm.request.ProductFilterRequest;

import java.util.List;

public interface ProductService {

    List<Product> getAllProduct();

    Product saveProduct(Product product);

    ProductResponse findProductById(long id);

    boolean existsById(Long id);




    void deleteProduct(Product product);

    void deleteById(Long id);

    List<ProductResponse> searchProducts(String query);

    List<ProductResponse> getProductsByProductNameProductPriceProdcutBrandAndCategory(ProductFilterRequest productFilterRequest);



    List<ProductResponse> getAllProductDTO();

    Product getProductById(Long id);



}
