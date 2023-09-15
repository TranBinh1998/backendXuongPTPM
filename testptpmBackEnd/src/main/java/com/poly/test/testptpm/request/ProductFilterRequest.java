package com.poly.test.testptpm.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductFilterRequest {

    private  String productName;;

    private   Double sellPrice;

    private Long idBrand;

    private Long idSubCategory;

    private Long idStatus;
}
