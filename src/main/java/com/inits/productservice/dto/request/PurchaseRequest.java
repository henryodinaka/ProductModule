package com.inits.productservice.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by Odinaka Onah on 02 Feb, 2021.
 */
@Data
public class PurchaseRequest {
    private String id;
    private int quantity;
    @NotBlank(message = "Product Id cannot be null")
    private String productId;
}
