package com.inits.productservice.dto.response;

import com.inits.productservice.model.PurchaseHistory;
import lombok.Data;

/**
 * Created by Odinaka Onah on 02 Feb, 2021.
 */
@Data
public class PurchaseResponse {
    private String id;
    private int quantity;
    private double totalAmount;
    private String status;
    private String createdAt;

    public PurchaseResponse(PurchaseHistory response) {
        this.id = response.getId();
        this.quantity = response.getQuantity();
        this.totalAmount = response.getTotalAmount();
        this.status = response.getStatus();
        this.createdAt = response.getCreatedAt();
    }
}
