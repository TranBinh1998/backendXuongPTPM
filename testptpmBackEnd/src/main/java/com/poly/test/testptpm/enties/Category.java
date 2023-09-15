package com.poly.test.testptpm.enties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    @Size(max = 20)
    private String cateCode;

    @Column(length = 100)
    @Size(max = 100)
    private String cateName;

    @OneToMany(mappedBy = "category")
    private Set<SubCategory> subCategories;
}
