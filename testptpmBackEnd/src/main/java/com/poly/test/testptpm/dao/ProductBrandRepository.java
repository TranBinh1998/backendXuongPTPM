package com.poly.test.testptpm.dao;

import com.poly.test.testptpm.enties.Brand;
import com.poly.test.testptpm.enties.Product;
import com.poly.test.testptpm.enties.ProductBrand;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductBrandRepository extends JpaRepository<ProductBrand, Long> {

    @Query("DELETE FROM ProductBrand pb WHERE pb.productId.id=:productId ")
    @Modifying
    @Transactional
    void deleteAllByProductId(@Param("productId") Long productId);


    @Query("SELECT pb " +
            " FROM ProductBrand pb" +
            " WHERE pb.productId.id = :productId")
    ProductBrand findProductBrandByBrandIdAndProductId( @Param("productId") Long productId);

}
