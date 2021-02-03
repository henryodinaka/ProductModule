package com.inits.productservice.service.impl;

import com.inits.productservice.constants.PurchaseStatus;
import com.inits.productservice.constants.ResponseCode;
import com.inits.productservice.dto.request.ProductRequest;
import com.inits.productservice.dto.request.PurchaseRequest;
import com.inits.productservice.dto.response.ProductResponse;
import com.inits.productservice.dto.response.PurchaseResponse;
import com.inits.productservice.exception.ProductException;
import com.inits.productservice.model.Product;
import com.inits.productservice.model.PurchaseHistory;
import com.inits.productservice.repository.ProductRepository;
import com.inits.productservice.service.ProductService;
import com.inits.productservice.service.PurchaseService;
import com.inits.productservice.utils.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Odinaka Onah on 02 Feb, 2021.
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private PurchaseService purchaseService;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setOrderService(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    public ProductResponse addOrProduct(ProductRequest request, boolean isUpdate) throws ProductException {
        log.info("Incoming Product request payload {}", request);
        if (request == null)
            throw new ProductException(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getValue().replace("{}", "request payload cannot be null"), ResponseCode.BAD_REQUEST.getStatusCode());
        var err = Validation.validateAmountAndQnty(request.getCost(), request.getQuantity());
        if (err != null)
            throw new ProductException(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getValue().replace("{}", err), ResponseCode.BAD_REQUEST.getStatusCode());
        Product product;
        if (isUpdate) {
            product = productRepository.findById(request.getId()).orElseThrow(() -> new ProductException(ResponseCode.ITEM_NOT_FOUND.getCode(), ResponseCode.ITEM_NOT_FOUND.getValue().replace("{}", "the product"), ResponseCode.ITEM_NOT_FOUND.getStatusCode()));
            product.setCost(request.getCost());
            product.setImageUrl(request.getImageUrl());
            product.setName(request.getName());
            product.setQuantity(request.getQuantity());
        }
        product = Product.builder()
                .name(request.getName())
                .quantity(request.getQuantity())
                .cost(request.getCost())
                .imageUrl(request.getImageUrl())
                .build();
        product = productRepository.save(product);
        return new ProductResponse(product);
    }

    public List<ProductResponse> listAllProducts() {
        var all = productRepository.findAll();
        log.info("All Products {}",all);
        return all.stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    public ProductResponse getProductsDetail(String productId) {
        var product = productRepository.findById(productId);
        return product.map(ProductResponse::new).orElse(null);
    }

    public PurchaseResponse placeOrUpdatePurchaseOrder(PurchaseRequest purchaseRequest, boolean isUpdate) throws ProductException {
        log.info("Incoming Purchase request payload {}", purchaseRequest);
        if (purchaseRequest == null)
            throw new ProductException(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getValue().replace("{}", "Order request Payload cannot be null"), ResponseCode.BAD_REQUEST.getStatusCode());
        if (purchaseRequest.getQuantity() < 1)
            throw new ProductException(ResponseCode.BAD_REQUEST.getCode(), ResponseCode.BAD_REQUEST.getValue().replace("{}", "product quantity must be at least 1"), ResponseCode.BAD_REQUEST.getStatusCode());
        Product product = productRepository.findById(purchaseRequest.getProductId()).orElseThrow(() -> new ProductException(ResponseCode.ITEM_NOT_FOUND.getCode(), ResponseCode.ITEM_NOT_FOUND.getValue().replace("{}", "prodcut"), ResponseCode.ITEM_NOT_FOUND.getStatusCode()));
        PurchaseHistory purchaseHistory;
        if (product.getQuantity() < purchaseRequest.getQuantity()) {
            throw new ProductException(ResponseCode.OUT_OF_STOCK.getCode(), ResponseCode.OUT_OF_STOCK.getValue().replace("{}", ""+product.getQuantity()), ResponseCode.OUT_OF_STOCK.getStatusCode());
        }

        var totalAmount = product.getCost() * purchaseRequest.getQuantity();
        var quantityInStock = product.getQuantity() - purchaseRequest.getQuantity();
        if (isUpdate) {
            purchaseHistory = purchaseService.findById(purchaseRequest.getId());
            purchaseHistory.setQuantity(purchaseRequest.getQuantity());
            purchaseHistory.setTotalAmount(totalAmount);
            purchaseHistory = purchaseService.save(purchaseHistory);
            purchaseService.callOrderService(purchaseHistory, product, true);
            product.setQuantity(Math.max(quantityInStock, 0));
            productRepository.save(product);
            return new PurchaseResponse(purchaseHistory);
        }
        purchaseHistory = PurchaseHistory.builder()
                .status(PurchaseStatus.PENDING.name())
                .quantity(purchaseRequest.getQuantity())
                .totalAmount(totalAmount)
                .build();
        product.getOrderHistories().add(purchaseHistory);
        product.setQuantity(Math.max(quantityInStock, 0));
        productRepository.save(product);
        purchaseService.callOrderService(purchaseHistory, product, false);
        return new PurchaseResponse(purchaseHistory);
    }

    public List<PurchaseResponse> listProductPurchaseHistories(String productId) throws ProductException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductException(ResponseCode.ITEM_NOT_FOUND.getCode(), ResponseCode.ITEM_NOT_FOUND.getValue().replace("{}", "product"), ResponseCode.ITEM_NOT_FOUND.getStatusCode()));
        return product.getOrderHistories().stream().map(PurchaseResponse::new).collect(Collectors.toList());
    }
}
