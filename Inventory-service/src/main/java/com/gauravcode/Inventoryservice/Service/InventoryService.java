package com.gauravcode.Inventoryservice.Service;

import com.gauravcode.Inventoryservice.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {

    List<InventoryResponse> isInStock(List<String> skuCode);
}
