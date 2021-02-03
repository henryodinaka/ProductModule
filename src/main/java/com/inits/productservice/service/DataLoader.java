package com.inits.productservice.service;

import com.inits.productservice.dto.request.PurchaseRequest;
import com.inits.productservice.exception.ProductException;
import com.inits.productservice.model.Product;
import com.inits.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by Odinaka Onah on 02 Feb, 2021.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {


    private final ProductService productService;

    private final ProductRepository productRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        seedProducts();
    }

    private void seedProducts() {
        var products = productRepository.findAll();
        if (products.isEmpty()) {
            products = new ArrayList<>();
            Product product1 = Product.builder()
                    .name("Shoe")
                    .quantity(20)
                    .cost(4000)
                    .imageUrl("https:://path-to-image-on-s3-bucket")
                    .build();
            Product product2 = Product.builder()
                    .name("Cap")
                    .quantity(200)
                    .cost(500)
                    .imageUrl("https:://path-to-image-on-s3-bucket")
                    .build();
            products.add(product1);
            products.add(product2);
            products = productRepository.saveAll(products);

           products.forEach(p -> {
               try {
                   productService.placeOrUpdatePurchaseOrder(PurchaseRequest.builder().productId(p.getId()).quantity(3).build(), false);
               } catch (ProductException e) {
                   log.error("Failed to load order ",e);
               }
           });
        }
    }

}
