package com.bosonit.order.service.dto;

import com.bosonit.order.service.model.OrderLineItems;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    private List<OrderLineItemsDto> orderLineItemsDtoList;
}
