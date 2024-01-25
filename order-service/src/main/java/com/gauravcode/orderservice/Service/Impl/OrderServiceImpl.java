package com.gauravcode.orderservice.Service.Impl;

import com.gauravcode.orderservice.Entity.Order;
import com.gauravcode.orderservice.Entity.OrderLineItems;
import com.gauravcode.orderservice.Repository.OrderRepository;
import com.gauravcode.orderservice.Service.OrderService;
import com.gauravcode.orderservice.dto.InventoryResponse;
import com.gauravcode.orderservice.dto.OrderLineItemsDto;
import com.gauravcode.orderservice.dto.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    private OrderRepository orderRepo;
    private WebClient webClient;

    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order =new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> list = orderRequest.getOrderLineDto().stream().map(this::mapToDto).toList();
        order.setOrderLine(list);
        List<String> skuCodes = order.getOrderLine().stream().map(OrderLineItems::getSkuCode).toList();
        //Call Inventory Service and place order if product isin stock
        InventoryResponse[] result = webClient.get().uri("http://localhost:8094/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCodes",skuCodes).build())
                .retrieve().bodyToMono(InventoryResponse[].class).block();
        boolean b = Arrays.stream(result).allMatch(InventoryResponse::getIsInStock);
        if(b){
            orderRepo.save(order);
        }
        else{
            throw new IllegalArgumentException("Product is not in stock-- please try again later|");
        }

    }

    public OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
