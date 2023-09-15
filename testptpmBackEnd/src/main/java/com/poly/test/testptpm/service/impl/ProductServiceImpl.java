package com.poly.test.testptpm.service.impl;

import com.poly.test.testptpm.dao.ProductRepository;
import com.poly.test.testptpm.dto.ProductDTO;
import com.poly.test.testptpm.enties.Product;
import com.poly.test.testptpm.request.ProductFilterRequest;
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
    public Product saveProduct(Product product) {

        return productRepository.saveAndFlush(product);
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
    public List<ProductDTO> searchProducts(String query) {
        return productRepository.searchProducts(query);
    }

    @Override
    public List<ProductDTO> getProductsByProductNameProductPriceProdcutBrandAndCategory(ProductFilterRequest productFilterRequest) {
        boolean isEmptyBrands = false;
        if (productFilterRequest.getIdBrand() == null || productFilterRequest.getIdBrand() == 0) {
            isEmptyBrands = true;
        }
        boolean isEmptyCategory = false;
        if (productFilterRequest.getIdSubCategory() == null || productFilterRequest.getIdSubCategory() == 0) {
            isEmptyCategory = true;
        }

        boolean isEmptyStatus = false;
        if (productFilterRequest.getIdStatus() == null || productFilterRequest.getIdStatus() == 0) {
            isEmptyStatus = true;
        }

        boolean isEmptyProductName = false;

        if ( productFilterRequest.getProductName() == null || productFilterRequest.getProductName().isEmpty()) {
            isEmptyProductName = true;
        }
        boolean isEmptyProductPrice = false;

        if ( productFilterRequest.getSellPrice() == null || productFilterRequest.getSellPrice() == 0) {
            isEmptyProductPrice = true;
        }

        List<ProductDTO> productDTOList = productRepository
                .getProductsByProductNameProductPriceProdcutBrandAndCategory(
                        isEmptyBrands, isEmptyCategory,
                        productFilterRequest.getIdBrand(),
                        productFilterRequest.getIdSubCategory(),
                        isEmptyProductName, productFilterRequest.getProductName(),
                        isEmptyProductPrice, productFilterRequest.getSellPrice(),
                        isEmptyStatus, productFilterRequest.getIdStatus()
                );

        return productDTOList;
    }


    @Override
    public List<ProductDTO> getAllProductDTO() {
        return productRepository.getAllProductDTO();
    }

    @Override
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return product;
    }


}
