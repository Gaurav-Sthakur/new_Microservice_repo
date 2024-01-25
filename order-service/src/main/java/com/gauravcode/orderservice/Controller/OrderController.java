package com.gauravcode.orderservice.Controller;

import com.gauravcode.orderservice.Service.OrderService;
import com.gauravcode.orderservice.dto.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
      orderService.placeOrder(orderRequest);
        return "order is placed successfully";
    }
}
