package com.poly.test.testptpm.dao;

import com.poly.test.testptpm.dto.ProductDTO;
import com.poly.test.testptpm.enties.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {


    // join 3 bảng để lấy ra 1 dto

    @Query("SELECT p FROM Product p WHERE " +
            "p.productName LIKE CONCAT('%',:query, '%')" +
            "Or p.description LIKE CONCAT('%', :query, '%')")
    List<Product> searchProducts(String query);

//    @Query ("SELECT p FROM Product p WHERE" +
//            " (:isEmptyBrands = true OR p.category.id IN :brands) " +
//            " and (:isEmptyCategory = true or p.)")
//    List<Product> findLaptopsByBrandAndSort(@Param("isEmptyBrands") boolean isEmptyBrands,
//                                            @Param("isEmptyCategory") boolean isEmptyCategory,
//                                            @Param ("brands") List<Long> idBrands,
//                                            @Param ("category") List<Long> idCategory);


    @Query(" SELECT new com.poly.test.testptpm.dto.ProductDTO(p.id, p.productName , b.brandName, p.category.cateName, p.color, p.quantity, p.sellPrice, p.originPrice, p.description ) " +
            " FROM Brand b LEFT JOIN ProductBrand pb ON b.id = pb.brandId.id " +
            " LEFT JOIN Product p ON p.id = pb.productId.id " +
            " WHERE (:isEmptyBrands = true OR pb.brandId.id in :brands) " +
            " AND (:isEmptyCategory = true OR p.category.id in :category) ")
    List<ProductDTO> findLaptopsByBrandAndCategory(@Param("isEmptyBrands") boolean isEmptyBrands,
                                                   @Param("isEmptyCategory") boolean isEmptyCategory,
                                                   @Param ("brands") List<Long> idBrands,
                                                   @Param ("category") List<Long> idCategory);


    // Tìm kiếm sản phẩm theo id

    @Query(" SELECT new com.poly.test.testptpm.dto.ProductDTO(p.id, p.productName , b.brandName, p.category.cateName, p.color, p.quantity, p.sellPrice, p.originPrice, p.description ) " +
            " FROM Brand b LEFT JOIN ProductBrand pb ON b.id = pb.brandId.id " +
            " LEFT JOIN Product p ON p.id = pb.productId.id " +
            " WHERE p.id = :productId ")
    List<ProductDTO> findByProductId(@Param("productId") Long id);

}
