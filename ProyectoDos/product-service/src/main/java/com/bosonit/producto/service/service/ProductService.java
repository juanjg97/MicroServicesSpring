package com.bosonit.producto.service.service;

import com.bosonit.producto.service.dto.ProductRequest;
import com.bosonit.producto.service.dto.ProductResponse;

import java.util.List;

public interface ProductService {
     ProductResponse createProduct(ProductRequest productRequest);
     List<ProductResponse> getAllProducts();

}
