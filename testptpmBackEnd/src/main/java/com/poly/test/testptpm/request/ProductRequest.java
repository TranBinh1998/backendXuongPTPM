package com.poly.test.testptpm.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequest {

    private Long id;

    private String productName;

    private Long idBrand;

    private Long idSubCategory;

    private String color;

    private Long quantity;

    private Double sellPrice;

    private Double originPrice;

    private String description;

    private Long idStatus;
}
