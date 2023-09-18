package com.poly.test.testptpm.enties;

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
import jakarta.validation.constraints.Size;
import lombok.Data;

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


    private String productName;


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
