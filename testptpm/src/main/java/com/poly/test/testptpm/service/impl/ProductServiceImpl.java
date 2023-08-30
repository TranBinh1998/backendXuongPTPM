package com.poly.test.testptpm.service.impl;

import com.poly.test.testptpm.dao.ProductRepository;
import com.poly.test.testptpm.dto.ProductDTO;
import com.poly.test.testptpm.enties.Product;
import com.poly.test.testptpm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.saveAndFlush(product);

    }

    @Override
    public ProductDTO findProductById(long id) {
        return productRepository.findByProductId(id).get(0);
    }

    @Override
    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> searchProducts(String query) {
        return productRepository.searchProducts(query);
    }

    @Override
    public List<ProductDTO> findLaptopsByBrandAndCategory(boolean isEmptyBrands, boolean isEmptyCategory, List<Long> brands, List<Long> category) {
        return productRepository.findLaptopsByBrandAndCategory(isEmptyBrands, isEmptyCategory, brands, category);
    }


}
