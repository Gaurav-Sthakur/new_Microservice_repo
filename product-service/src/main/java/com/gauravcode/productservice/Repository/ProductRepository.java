package com.gauravcode.productservice.Repository;

import com.gauravcode.productservice.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
