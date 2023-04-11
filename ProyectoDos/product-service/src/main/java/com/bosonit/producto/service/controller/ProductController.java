package com.bosonit.producto.service.controller;

import com.bosonit.producto.service.dto.ProductRequest;
import com.bosonit.producto.service.dto.ProductResponse;
import com.bosonit.producto.service.model.Product;
import com.bosonit.producto.service.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }
}
