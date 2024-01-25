package com.gauravcode.orderservice.Service;

import com.gauravcode.orderservice.dto.OrderRequest;

public interface OrderService {

    void placeOrder(OrderRequest orderRequest);
}
