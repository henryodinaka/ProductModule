package com.inits.productservice.service;

import com.inits.productservice.dto.request.PurchaseRequest;
import com.inits.productservice.dto.request.ProductRequest;
import com.inits.productservice.dto.response.Response;
import com.inits.productservice.exception.ProductException;
import org.springframework.http.ResponseEntity;

/**
 * Created by Odinaka Onah on 02 Feb, 2021.
 */
public interface ProductService {

    ResponseEntity<Response> addOrProduct(ProductRequest request, boolean isUpdate) throws ProductException;
    ResponseEntity<Response> placeOrUpdatePurchaseOrder(PurchaseRequest purchaseRequest, boolean isUpdate) throws ProductException;
    ResponseEntity<Response> listAllProducts();
    ResponseEntity<Response> getProductsDetail(String productId);
    ResponseEntity<Response> listProductPurchaseHistories(String productId) throws ProductException;
}
