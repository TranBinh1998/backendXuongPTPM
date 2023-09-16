package com.poly.test.testptpm.repository;

import com.poly.test.testptpm.enties.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>{
}
