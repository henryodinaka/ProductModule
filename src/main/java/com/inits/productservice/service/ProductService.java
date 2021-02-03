package com.inits.productservice.service;

import com.inits.productservice.dto.request.PurchaseRequest;
import com.inits.productservice.dto.request.ProductRequest;
import com.inits.productservice.dto.response.ProductResponse;
import com.inits.productservice.dto.response.PurchaseResponse;
import com.inits.productservice.dto.response.Response;
import com.inits.productservice.exception.ProductException;
import com.inits.productservice.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by Odinaka Onah on 02 Feb, 2021.
 */
public interface ProductService {

    ProductResponse addOrProduct(ProductRequest request, boolean isUpdate) throws ProductException;
    PurchaseResponse placeOrUpdatePurchaseOrder(PurchaseRequest purchaseRequest, boolean isUpdate) throws ProductException;
    List<ProductResponse> listAllProducts();
    ProductResponse getProductsDetail(String productId);
    List<PurchaseResponse> listProductPurchaseHistories(String productId) throws ProductException;
}
