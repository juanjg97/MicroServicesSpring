package com.bosonit.order.service.service;

import com.bosonit.order.service.dto.OrderRequest;
import com.bosonit.order.service.model.Order;

public interface OrderService {
    void createOrder(OrderRequest orderRequest);
}
