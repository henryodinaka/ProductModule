package com.inits.productservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Created by Odinaka Onah on 02 Feb, 2021.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    private String id;
    @NotBlank(message = "Product name cannot be blank")
    private String name;
    @NotBlank(message = "Product Image url cannot be blank")
    private String imageUrl;
    private int quantity;
    private double cost;
}
