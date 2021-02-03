package com.inits.productservice.service;

import com.inits.productservice.model.Product;
import com.inits.productservice.model.PurchaseHistory;

/**
 * Created by Odinaka Onah on 02 Feb, 2021.
 */
public interface PurchaseService {

    PurchaseHistory findById(String id);
    PurchaseHistory save(PurchaseHistory purchaseHistory);
    void callOrderService(PurchaseHistory history, Product product,boolean isUpdate);
}
