package com.gauravcode.productservice.Service;

import com.gauravcode.productservice.dto.ProductRequest;
import com.gauravcode.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    void createProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProduct();
}
