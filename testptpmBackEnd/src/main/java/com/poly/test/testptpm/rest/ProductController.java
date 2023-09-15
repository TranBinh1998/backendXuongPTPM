package com.poly.test.testptpm.rest;

import com.poly.test.testptpm.dao.ProductRepository;
import com.poly.test.testptpm.dto.ProductDTO;
import com.poly.test.testptpm.enties.Brand;
import com.poly.test.testptpm.enties.Product;
import com.poly.test.testptpm.enties.ProductBrand;
import com.poly.test.testptpm.enties.Status;
import com.poly.test.testptpm.enties.SubCategory;
import com.poly.test.testptpm.request.ProductFilterRequest;
import com.poly.test.testptpm.request.ProductRequest;
import com.poly.test.testptpm.service.BrandsService;
import com.poly.test.testptpm.service.ProductBrandService;
import com.poly.test.testptpm.service.ProductService;
import com.poly.test.testptpm.service.StatusService;
import com.poly.test.testptpm.service.SubCategoryService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private static final int PAGE_NUMBER = 36;


    ProductService productService;

    BrandsService brandsService;

    SubCategoryService subCategoryService;

    StatusService statusService;

    ProductBrandService productBrandService;


    @Autowired
    public ProductController(ProductService productService,
                             BrandsService brandsService,
                             SubCategoryService subCategoryService,
                             StatusService statusService,
                             ProductBrandService productBrandService) {
        this.productService = productService;
        this.brandsService = brandsService;
        this.subCategoryService = subCategoryService;
        this.statusService = statusService;
        this.productBrandService = productBrandService;
    }

    @Autowired


    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProductDTO() {

        List<ProductDTO> productDTOList = productService.getAllProductDTO();

        return ResponseEntity.ok(productDTOList);
    }


    @GetMapping("{id}")
    public ResponseEntity<?> getProductById(@PathVariable long id) {

        ProductDTO product = productService.findProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Tối ưu hàm để dùng chung cho update và save
    @PostMapping("/save-product")
    @Transactional
    public ResponseEntity<?> saveProduct(@Validated @RequestBody ProductRequest productRequest, BindingResult result) {
        try {
            if (result.hasErrors()) {
                Map<String, String> errors = new HashMap<>();
                errors.put("status", "error");
                errors.put("message", "Dữ liệu không hợp lệ");
                for (FieldError error : result.getFieldErrors()) {
                    errors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }

        boolean isUpdate = true;


        Product product = productService.getProductById(productRequest.getId());

        if (product == null) {
//                nếu là add thì new 1 product để gán;
            isUpdate = false;
            product = new Product();
        }
        Brand brand = brandsService.getBrandById(productRequest.getIdBrand());
        if (brand == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Brand không tồn tại, vui lòng kiểm tra lại");
        }
        SubCategory subCategory = subCategoryService.getSubCategoryById(productRequest.getIdSubCategory());
        if (subCategory == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("SubCategory không tồn tại");
        }

        Status status = statusService.getStatusById(productRequest.getIdStatus());

        product.setSubCategory(subCategory);
        product.setProductName(productRequest.getProductName());
        product.setId(productRequest.getId());
        product.setOriginPrice(productRequest.getOriginPrice());
        product.setDescription(productRequest.getDescription());
        product.setStatus(status);
        product.setQuantity(productRequest.getQuantity());
        product.setSellPrice(productRequest.getSellPrice());
        product.setColor(productRequest.getColor());
        Product savedProduct = productService.saveProduct(product);
        ProductBrand productBrand;
        if (isUpdate) {
            productBrandService.delAllByIdProduct(product.getId());
        }
        productBrand = new ProductBrand();
        productBrand.setProductId(savedProduct);
        productBrand.setBrandId(brand);

        productBrandService.saveProductBrand(productBrand);

        return ResponseEntity.status(isUpdate ? HttpStatus.OK : HttpStatus.CREATED).body(product);

    }


    @DeleteMapping("/del/{id}")
    @Transactional
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            if (product == null) {
                // Tạo một đối tượng chứa các thông tin lỗi
                Map<String, Object> errors = new HashMap<>();
                errors.put("status", "error");
                errors.put("message", "Không tìm thấy sản phẩm");
                errors.put("code", 404);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
            }

            productBrandService.delAllByIdProduct(product.getId());

            productService.deleteProduct(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Đã xóa");
    }

    // Tìm kiếm product
    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProducts(@RequestParam("query") String query) {

        List<ProductDTO> list = productService.searchProducts(query);
        if (list.isEmpty()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list); // trả ra server 1 list
    }


    @PostMapping("/filter-product")
    public ResponseEntity<?> filterPrduct(@RequestBody ProductFilterRequest productFilterRequest) {

        List<ProductDTO> productList = productService.getProductsByProductNameProductPriceProdcutBrandAndCategory(productFilterRequest);

        if (productList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(productList);
    }


}
