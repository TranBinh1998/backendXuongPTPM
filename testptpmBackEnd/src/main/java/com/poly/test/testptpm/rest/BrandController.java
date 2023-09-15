package com.poly.test.testptpm.rest;

import com.poly.test.testptpm.enties.Brand;
import com.poly.test.testptpm.service.BrandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {

    @Autowired
    BrandsService brandsService;

    @GetMapping
    public ResponseEntity<?> getAllBrands () {
        List<Brand> brandList = brandsService.getlistBrands();
        System.out.println("Gọi request");
        return ResponseEntity.ok(brandList);
    }

}
