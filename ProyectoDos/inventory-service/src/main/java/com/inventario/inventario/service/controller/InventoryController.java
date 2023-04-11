package com.inventario.inventario.service.controller;

import com.inventario.inventario.service.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
public class InventoryController {
    private InventoryService inventoryService;

    @GetMapping("/{skuCode}")
    public boolean isInStock(@PathVariable String skuCode){
        return inventoryService.isInStock(skuCode);
    }
}
