package com.gauravcode.orderservice.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class OrderRequest {

    List<OrderLineItemsDto> orderLineDto;
}
