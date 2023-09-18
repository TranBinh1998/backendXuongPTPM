package com.poly.test.testptpm.enties;

import com.poly.test.testptpm.util.ProductBrandId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "product_brand")
@IdClass(ProductBrandId.class)
public class ProductBrand {

    @Id
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brandId;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    // Có thể thay thế bằng @ManyToManyn ở bảng product.
}
