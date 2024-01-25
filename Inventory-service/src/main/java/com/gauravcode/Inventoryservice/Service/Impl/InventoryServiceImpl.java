package com.gauravcode.Inventoryservice.Service.Impl;

import com.gauravcode.Inventoryservice.Entity.Inventory;
import com.gauravcode.Inventoryservice.Repository.InventoryRepository;
import com.gauravcode.Inventoryservice.Service.InventoryService;
import com.gauravcode.Inventoryservice.dto.InventoryResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepository inRepo;

    public InventoryServiceImpl(InventoryRepository inRepo) {
        this.inRepo = inRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        Inventory inventory=new Inventory();
        return inRepo.findBySkuCode(skuCode).stream()
                .map(ve ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity() > 0)
                            .build()
                 ).toList();
    }
}
