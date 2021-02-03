package com.inits.productservice.service.impl;

import com.inits.productservice.constants.PurchaseStatus;
import com.inits.productservice.dto.request.OrderRequest;
import com.inits.productservice.model.Product;
import com.inits.productservice.model.PurchaseHistory;
import com.inits.productservice.repository.PurchaseRepository;
//import com.inits.productservice.restclient.OrderServiceApiCall;
import com.inits.productservice.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Odinaka Onah on 02 Feb, 2021.
 */
@Slf4j
@Service
//@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private PurchaseRepository purchaseRepository;
    @Value("{app.order-service.url}")
    private String orderServiceUrl;

    public PurchaseHistory findById(String id) {
        var orderHistory = purchaseRepository.findById(id);
        return orderHistory.orElse(null);
    }

    @Autowired
    public void setPurchaseRepository(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public PurchaseHistory save(PurchaseHistory purchaseHistory) {
        return purchaseRepository.save(purchaseHistory);
    }

    @Async
    public void callOrderService(PurchaseHistory history, Product product, boolean isUpdate) {
        var orderRequest = OrderRequest.builder()
                .orderId(history.getId())
                .productId(product.getId())
                .quantity(history.getQuantity())
                .totalAmount(history.getTotalAmount())
                .isUpdate(isUpdate)
                .build();
        log.info("Making an https call to Order service with url {} and request payload {}", orderServiceUrl, orderRequest);

        // TODO: 2/2/2021 Make api call to order service


    }
}
