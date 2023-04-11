package com.bosonit.producto.service.repository;

import com.bosonit.producto.service.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
