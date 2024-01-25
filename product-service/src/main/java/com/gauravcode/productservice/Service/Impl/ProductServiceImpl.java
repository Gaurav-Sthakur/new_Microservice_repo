package com.gauravcode.productservice.Service.Impl;

import com.gauravcode.productservice.Model.Product;
import com.gauravcode.productservice.Repository.ProductRepository;
import com.gauravcode.productservice.Service.ProductService;
import com.gauravcode.productservice.dto.ProductRequest;
import com.gauravcode.productservice.dto.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepo;

    public ProductServiceImpl(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public void createProduct(ProductRequest productRequest) {
        String id = UUID.randomUUID().toString();
        Product product= Product.builder().id(id)
                .name(productRequest.getName())
                .description(productRequest.getDescription()).price(productRequest.getPrice()).build();

        productRepo.save(product);
        log.info("Product {} is Saved",product.getId());
    }
    @Override
    public List<ProductResponse> getAllProduct(){

        List<Product> products = productRepo.findAll();
        return products.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public ProductResponse mapToResponse(Product product) {

       return  ProductResponse.builder().id(product.getId())
                .name(product.getName()).description(product.getDescription()).price(product.getPrice()).build();
    }
}
