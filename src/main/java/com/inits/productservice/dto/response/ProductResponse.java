package com.inits.productservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inits.productservice.model.Product;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Odinaka Onah on 02 Feb, 2021.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {
    private String id;
    private String name;
    private String imageUrl;
    private int quantity;
    private double cost;
    private Set<PurchaseResponse> purchaseRespons;
    private String createdAt;
    private String updatedAt;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.imageUrl = product.getImageUrl();
        this.quantity = product.getQuantity();
        this.cost = product.getCost();
        this.createdAt = product.getCreatedAt();
        this.updatedAt = product.getUpdatedAt();
        if (product.getOrderHistories() != null && !product.getOrderHistories().isEmpty())
            this.purchaseRespons = product.getOrderHistories().stream().map(PurchaseResponse::new).collect(Collectors.toSet());
    }
}
