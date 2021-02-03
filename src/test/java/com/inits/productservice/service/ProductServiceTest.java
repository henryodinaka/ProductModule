package com.inits.productservice.service;

import com.inits.productservice.constants.PurchaseStatus;
import com.inits.productservice.dto.request.ProductRequest;
import com.inits.productservice.dto.request.PurchaseRequest;
import com.inits.productservice.dto.response.PurchaseResponse;
import com.inits.productservice.exception.ProductException;
import com.inits.productservice.model.Product;
import com.inits.productservice.model.PurchaseHistory;
import com.inits.productservice.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Odinaka Onah on 03 Feb, 2021.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    private Product product;

    @Before
    public void setUp() {
        var pc = Product.builder().quantity(30).name("Dell Pc").imageUrl("path-to-image").cost(900000).createdAt(LocalDateTime.now()).orderHistories(new HashSet<>()).build();
        List<PurchaseHistory> purchaseHistories = Arrays.asList(PurchaseHistory.builder().id("29948-3884803-838498").build(), PurchaseHistory.builder().id("38984-909483-894893").build());
        pc.getOrderHistories().addAll(purchaseHistories);
        product = productRepository.save(pc);
        System.out.println("Saved product "+product);
    }
    @After
    public void cleanUp(){
        productRepository.deleteAll();
    }
    @Test
    public void testAddOrProduct() throws ProductException {
        var productRequest = ProductRequest.builder()
                .cost(3000)
                .name("Gold")
                .imageUrl("https://path-to-image")
                .quantity(50)
                .build();
        var productResponse = productService.addOrProduct(productRequest, false);
        Assertions.assertThat(productResponse).isNotNull();
        Assertions.assertThat(productResponse.getName()).isEqualTo("Gold");
    }

    @Test
    public void testListAllProducts() {
        var productResponses = productService.listAllProducts();
        Assertions.assertThat(productResponses).isNotNull();
        Assertions.assertThat(productResponses.size()).isEqualTo(1);
    }

    @Test
    public void testGetProductsDetail() {
        var productsDetail = productService.getProductsDetail(product.getId());
        Assertions.assertThat(productsDetail).isNotNull();
        Assertions.assertThat(productsDetail.getId()).isEqualTo(product.getId());
    }

    @Test
    public void testPlaceOrUpdatePurchaseOrder() throws ProductException {
        var purchaseRequest = PurchaseRequest.builder().productId(product.getId()).quantity(2).build();
        var purchaseResponse = productService.placeOrUpdatePurchaseOrder(purchaseRequest, false);
        Assertions.assertThat(purchaseResponse).isNotNull();
        Assertions.assertThat(purchaseResponse.getStatus()).isEqualTo(PurchaseStatus.PLACED.name());
    }

    @Test
    public void testListProductPurchaseHistories() throws ProductException {
        var purchaseResponses = productService.listProductPurchaseHistories(product.getId());
        Assertions.assertThat(purchaseResponses).isNotNull();
        Assertions.assertThat(purchaseResponses.size()).isEqualTo(2);
    }
}
