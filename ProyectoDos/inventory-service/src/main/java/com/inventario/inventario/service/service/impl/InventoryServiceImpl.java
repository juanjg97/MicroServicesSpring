package com.inventario.inventario.service.service.impl;

import com.inventario.inventario.service.repository.InventoryRerpository;
import com.inventario.inventario.service.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private InventoryRerpository inventoryRerpository;

    @Transactional(readOnly = true)
    public boolean isInStock(String  skuCode) {
        return inventoryRerpository.findBySkuCode(skuCode).isPresent();
    }
}
