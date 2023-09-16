package com.poly.test.testptpm.service.impl;

import com.poly.test.testptpm.repository.SubCategoryRepository;
import com.poly.test.testptpm.enties.SubCategory;
import com.poly.test.testptpm.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    private SubCategoryRepository subCategoryRepository;

    @Autowired
    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public List<SubCategory> getAllSubCategoryList() {
        return subCategoryRepository.findAll();
    }

    @Override
    public SubCategory getSubCategoryById(Long id) {
        return subCategoryRepository.findById(id).orElse(null);
    }
}
