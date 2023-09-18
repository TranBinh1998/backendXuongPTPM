package com.poly.test.testptpm.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequest {

        private Long id;

        @Size(min = 2,max = 100, message = "Tên phải có độ dài từ 2 đến 100 ký tự")
        @NotBlank(message = "Tên sản phẩm không được để trống")
        private String productName;

        private Long idBrand;

        private Long idSubCategory;

        @Size(min = 2,max = 49, message = "Màu sắc phải có độ dài từ 2 đến 50 ký tự")
        @NotBlank(message = "Màu sắc không được để trống")
        @Pattern(regexp = "^[\\p{L}\\p{N}\\s]*$", message = "Kí tự đặc biệt không hợp lệ")
        private String color;

        @Positive(message = "Số lượng phải là một số nguyên dương")
        private Long quantity;

        @Positive(message = "Gía bán phải là một số nguyên dương")
        @Min(value = 1000, message = "Gía bán không được dưới 1000")
        @NotNull(message = "Sell Price không được để trống")
        private Double sellPrice;

        @Min(value = 1000, message = "Gía bán không được dưới 1000")
        @Positive(message = "Gía nhập phải là một số nguyên dương")
        @NotNull(message = "Origin Price không được để trống")
        private Double originPrice;

        @NotBlank(message = "Chi tiết sản phẩm không được để trống")
        private String description;

        private Long idStatus;
}
