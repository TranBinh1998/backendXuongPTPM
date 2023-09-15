package com.poly.test.testptpm.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    private String productName;

    private String nameBrand;

    private String nameCategory;

    private String color;

    private Long quantity;

    private Double sellPrice;

    private Double originPrice;

    private String description;

    private String status;

    private Long idBrand;

    private Long idStatus;

    private Long idSubCategory;

}
