package com.gauravcode.Inventoryservice.Repository;

import com.gauravcode.Inventoryservice.Entity.Inventory;
import com.gauravcode.Inventoryservice.dto.InventoryResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    List<InventoryResponse> findBySkuCode(List<String> skuCode);
}
