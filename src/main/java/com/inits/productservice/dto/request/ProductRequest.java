package com.inits.productservice.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by Odinaka Onah on 02 Feb, 2021.
 */
@Data
public class ProductRequest {
    private String id;
    @NotBlank(message = "Product name cannot be blank")
    private String name;
    @NotBlank(message = "Product Image url cannot be blank")
    private String imageUrl;
    private int quantity;
    private double cost;
}
