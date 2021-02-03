package com.inits.productservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Odinaka Onah on 02 Feb, 2021.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private String orderId;
    private int quantity;
    private double totalAmount;
    private String productId;
    private boolean isUpdate;
}
