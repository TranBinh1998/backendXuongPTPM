package com.poly.test.testptpm.util;


import lombok.Data;

import java.io.Serializable;

@Data
public class ProductBrandId implements Serializable {

    private Long productId;

    private Long brandId;
}
