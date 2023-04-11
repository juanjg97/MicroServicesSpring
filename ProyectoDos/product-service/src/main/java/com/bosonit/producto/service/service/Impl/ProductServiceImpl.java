package com.bosonit.producto.service.service.Impl;

import com.bosonit.producto.service.dto.ProductRequest;
import com.bosonit.producto.service.dto.ProductResponse;
import com.bosonit.producto.service.model.Product;
import com.bosonit.producto.service.repository.ProductRepository;
import com.bosonit.producto.service.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .productName(productRequest.getProductName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("The product {} has been created.", product);
        return mapToPorductResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToPorductResponse).collect(Collectors.toList());
    }

    private ProductResponse mapToPorductResponse(Product product){
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
