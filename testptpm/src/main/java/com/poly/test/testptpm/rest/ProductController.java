package com.poly.test.testptpm.rest;

import com.poly.test.testptpm.dao.ProductRepository;
import com.poly.test.testptpm.dto.ProductDTO;
import com.poly.test.testptpm.enties.Product;
import com.poly.test.testptpm.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final int PAGE_NUMBER = 36;

    @Autowired
    ProductService productService;


    @GetMapping
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }


    @GetMapping("{id}")
    public ResponseEntity<?> getStudentById(@PathVariable long id) {

        ProductDTO product = productService.findProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Tối ưu hàm để dùng chung cho update và save

    @PostMapping("/save-product")
    public ResponseEntity<?> saveProduct(@Validated @RequestBody Product product, BindingResult result) {

        if (result.hasErrors()) {

            Map<String, String> errors = new HashMap<>();
            errors.put("status", "error");
            errors.put("message", "Dữ liệu không hợp lệ");
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        boolean isUpdate = productService.existsById(product.getId());

        if (isUpdate) {
            productService.saveProduct(product);
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            product.setId(Long.parseLong("0"));
            productService.saveProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        }
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        boolean isProduct = productService.existsById(id);
        if (!isProduct) {
            // Tạo một đối tượng chứa các thông tin lỗi
            Map<String, Object> errors = new HashMap<>();
            errors.put("status", "error");
            errors.put("message", "Không tìm thấy sản phẩm");
            errors.put("code", 404);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
        }
        productService.deleteById(id);
        // Tạo một đối tượng chứa các thông tin thành công
        Map<String, Object> success = new HashMap<>();
        success.put("status", "success");
        success.put("message", "Xóa sản phẩm thành công");
        success.put("data", null);
        // Trả về một mã trạng thái HTTP 200 (OK) và đối tượng success
        return ResponseEntity.ok(success);
    }

    // Tìm kiếm product
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam("query") String query) {

        List<Product> list = productService.searchProducts(query);
        return ResponseEntity.ok(list); // trả ra server 1 list
    }

    //Lọc sản phẩm
    // Lọc theo giá tiền sellPrice , Lọc theo màu sắc color, lọc theo hãng, lọc theo loại

    @GetMapping("/filter-product")
    public ResponseEntity<?> filterPrduct(@RequestParam(name = "hang-san-xuat", required = false) List<Long> brands,
                                          @RequestParam(name = "danh-muc", required = false) List<Long> category) {
        boolean isEmptyBrands = false;
        if (brands == null || category.isEmpty()) {
            isEmptyBrands = true;
        }
        boolean isEmptyCategory = false;
        if (category == null || category.isEmpty()) {
            isEmptyCategory = true;
        }
        List<ProductDTO> productList = productService.findLaptopsByBrandAndCategory(isEmptyBrands, isEmptyCategory, brands, category);
        if (productList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(productList);
    }


}
