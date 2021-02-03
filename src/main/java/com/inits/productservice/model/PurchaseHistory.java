package com.inits.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.inits.productservice.utils.CommonUtils;
import com.inits.productservice.utils.Validation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by Odinaka Onah on 02 Feb, 2021.
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PurchaseHistory {
    @Id
    private String id = UUID.randomUUID().toString();
    private int quantity;
    private double totalAmount;
    @NotBlank(message = "Order status cannot be null")
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void beforeSave() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void beforeUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public String getCreatedAt() {
        return Validation.validData(createdAt) ? CommonUtils.dateToStringFormated(createdAt) : " ";
    }

}
