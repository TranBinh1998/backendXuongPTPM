package com.poly.test.testptpm.enties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Entity
@Data
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "name_category_id")
    private SubCategory subCategory;

    @Column(name = "product_name", length = 100)
    @Size(min = 2,max = 100, message = "Tên phải có độ dài từ 2 đến 100 ký tự")
    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String productName;

    @Size(min = 2,max = 49, message = "Màu sắc phải có độ dài từ 2 đến 50 ký tự")
    @NotBlank(message = "Màu sắc không được để trống")
    @Column(name = "color", length = 50)
    private String color;

    private Long quantity;

    private Double sellPrice;

    private Double originPrice;

    @Column(length = 1000)
    @Size(min = 2,max = 1000)
    private String description;

    @OneToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @OneToMany(mappedBy = "productId")
    Set<ProductBrand> productBrand;

}
