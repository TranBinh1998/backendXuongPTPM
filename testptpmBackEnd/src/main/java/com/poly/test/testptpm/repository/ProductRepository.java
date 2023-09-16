package com.poly.test.testptpm.repository;

import com.poly.test.testptpm.response.ProductResponse;
import com.poly.test.testptpm.enties.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    // join 3 bảng để lấy ra 1 dto

    @Query("SELECT new com.poly.test.testptpm.response.ProductResponse(p.id, p.productName , b.brandName, p.subCategory.subCateName, p.color, p.quantity, p.sellPrice, p.originPrice, p.description, p.status.statusName, b.id, p.status.id, p.subCategory.id ) " +
            " FROM Brand b LEFT JOIN ProductBrand pb ON b.id = pb.brandId.id" +
            " LEFT JOIN Product p ON p.id = pb.productId.id" +
            " WHERE " +
            " p.productName LIKE CONCAT('%',:query, '%')" +
            " Or p.description LIKE CONCAT('%', :query, '%')  order by p.sellPrice desc")
    List<ProductResponse> searchProducts(String query);


    @Query(" SELECT new com.poly.test.testptpm.response.ProductResponse(p.id, p.productName , b.brandName, p.subCategory.subCateName, p.color, p.quantity, p.sellPrice, p.originPrice, p.description, p.status.statusName, b.id, p.status.id, p.subCategory.id ) " +
            " FROM Brand b LEFT JOIN ProductBrand pb ON b.id = pb.brandId.id " +
            " LEFT JOIN Product p ON p.id = pb.productId.id " +
            " WHERE (:isEmptyBrands = true OR pb.brandId.id in :brands) " +
            " AND (:isEmptyCategory = true OR p.subCategory.id in :category)  order by p.sellPrice desc ")
    List<ProductResponse> findLaptopsByBrandAndCategory(@Param("isEmptyBrands") boolean isEmptyBrands,
                                                        @Param("isEmptyCategory") boolean isEmptyCategory,
                                                        @Param("brands") List<Long> idBrands,
                                                        @Param("category") List<Long> idCategory);


    @Query(" SELECT new com.poly.test.testptpm.response.ProductResponse(p.id, p.productName , b.brandName, p.subCategory.subCateName, p.color, p.quantity, p.sellPrice, p.originPrice, p.description, p.status.statusName, b.id, p.status.id, p.subCategory.id ) " +
            " FROM Brand b LEFT JOIN ProductBrand pb ON b.id = pb.brandId.id " +
            " LEFT JOIN Product p ON p.id = pb.productId.id " +
            " WHERE (:isEmptyBrands = true OR pb.brandId.id in :brands) " +
            " AND (:isEmptyCategory = true OR p.subCategory.id in :category) " +
            " and (:isEmptyStatus = true  or p.status.id = :statusId)" +
            " and (:isEmptyProductName = true or p.productName like %:productName% ) " +
            " and (:isEmptyProductPrice = true or p.sellPrice <= :productPrice)  order by p.sellPrice desc ")
    List<ProductResponse> getProductsByProductNameProductPriceProdcutBrandAndCategory(
            @Param("isEmptyBrands") boolean isEmptyBrands,
            @Param("isEmptyCategory") boolean isEmptyCategory,
            @Param("brands") Long brandId,
            @Param("category") Long categoryId,
            @Param("isEmptyProductName") boolean isEmptyProductName,
            @Param("productName") String productName,
            @Param("isEmptyProductPrice") boolean isEmptyProductPrice,
            @Param("productPrice") Double productPrice,
            @Param("isEmptyStatus") boolean isEmptyStatus,
            @Param("statusId") Long statusId);

    // Tìm kiếm sản phẩm theo id

    @Query(" SELECT new com.poly.test.testptpm.response.ProductResponse(p.id, p.productName , b.brandName, p.subCategory.subCateName, p.color, p.quantity, p.sellPrice, p.originPrice, p.description, p.status.statusName, b.id, p.status.id , p.subCategory.id ) " +
            " FROM Brand b LEFT JOIN ProductBrand pb ON b.id = pb.brandId.id " +
            " LEFT JOIN Product p ON p.id = pb.productId.id " +
            " WHERE p.id = :productId ")
    List<ProductResponse> findByProductId(@Param("productId") Long id);


    @Query(" SELECT new com.poly.test.testptpm.response.ProductResponse(p.id, p.productName , b.brandName, p.subCategory.subCateName, p.color, p.quantity, p.sellPrice, p.originPrice, p.description, p.status.statusName , b.id, p.status.id , p.subCategory.id ) " +
            " FROM Brand b LEFT JOIN ProductBrand pb ON b.id = pb.brandId.id " +
            " LEFT JOIN Product p ON p.id = pb.productId.id order by p.sellPrice desc")
    List<ProductResponse> getAllProductDTO();
}
