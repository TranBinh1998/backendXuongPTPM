package com.poly.test.testptpm.controller;


import com.poly.test.testptpm.enties.SubCategory;
import com.poly.test.testptpm.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/subcategory")
public class SubCategoryController {

    private SubCategoryService subCategoryService;

    @Autowired
    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAllBrands () {
        List<SubCategory> subCategoryList = subCategoryService.getAllSubCategoryList();
        return ResponseEntity.ok(subCategoryList);
    }
}
