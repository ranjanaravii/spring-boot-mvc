package com.developer.product_service.controller;

import com.developer.product_service.dto.ProductRequest;
import com.developer.product_service.dto.ProductResponse;
import com.developer.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void rxCreateProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> rxGetAllProduct() {
        return productService.rxGetAllProduct();
    }
}
