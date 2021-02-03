package com.inits.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.inits.productservice.utils.CommonUtils;
import com.inits.productservice.utils.Validation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
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
public class Product {
    @Id
    private String id = UUID.randomUUID().toString();
    @NotBlank(message = "Product name cannot be blank")
    private String name;
    private int quantity;
    private double cost;
    @NotBlank(message = "Image url cannot be empty")
    private String imageUrl;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "ProductOrder", joinColumns = @JoinColumn(referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
    private Set<PurchaseHistory> orderHistories  = new HashSet<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void beforeSave() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void beforeUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public String getCreatedAt() {
        return Validation.validData(createdAt) ? CommonUtils.dateToStringFormated(createdAt) : " ";
    }

    public String getUpdatedAt() {
        return Validation.validData(updatedAt) ? CommonUtils.dateToStringFormated(updatedAt) : " ";
    }
}
