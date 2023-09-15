package com.poly.test.testptpm.service;

import com.poly.test.testptpm.enties.Category;
import com.poly.test.testptpm.enties.SubCategory;

import java.util.List;

public interface SubCategoryService {



    List<SubCategory> getAllSubCategoryList ();

    SubCategory getSubCategoryById(Long id);
}
