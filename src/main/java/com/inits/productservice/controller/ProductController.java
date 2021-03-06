package com.inits.productservice.controller;

import com.inits.productservice.constants.ResponseCode;
import com.inits.productservice.dto.request.ProductRequest;
import com.inits.productservice.dto.request.PurchaseRequest;
import com.inits.productservice.dto.response.Response;
import com.inits.productservice.exception.ProductException;
import com.inits.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Odinaka Onah on 02 Feb, 2021.
 */
@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.PATCH, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT})
@Slf4j
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Response> addProduct(ProductRequest request) throws ProductException {
        return Response.setUpResponse(ResponseCode.SUCCESS, "Product was added ",productService.addOrProduct(request, false));
    }

    @PatchMapping
    public ResponseEntity<Response> updateProduct(ProductRequest request) throws ProductException {
        return Response.setUpResponse(ResponseCode.SUCCESS, "Product was added ",productService.addOrProduct(request, true));
    }

    @GetMapping
    public ResponseEntity<Response> listAllProducts() {
        return Response.setUpResponse(ResponseCode.SUCCESS, "Product listed",productService.listAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getProductsDetail(@PathVariable String id) {
        var value = productService.getProductsDetail(id);
        return value !=null?Response.setUpResponse(ResponseCode.SUCCESS, "product retrieved",value): Response.setUpResponse(ResponseCode.ITEM_NOT_FOUND, "product");
    }

    @PostMapping("/order")
    public ResponseEntity<Response> placeOrder(@RequestBody PurchaseRequest purchaseRequest) throws ProductException {
        return Response.setUpResponse(ResponseCode.SUCCESS, "Order placed ", productService.placeOrUpdatePurchaseOrder(purchaseRequest, false));
    }

    @PatchMapping("/order")
    public ResponseEntity<Response> updateOrder(@RequestBody PurchaseRequest purchaseRequest) throws ProductException {
        return Response.setUpResponse(ResponseCode.SUCCESS, "Order placed ", productService.placeOrUpdatePurchaseOrder(purchaseRequest, true));
    }

    @GetMapping("/{productId}/order")
    public ResponseEntity<Response> listProductOrderHistories(@PathVariable String productId) throws ProductException {
        return Response.setUpResponse(ResponseCode.SUCCESS, "Order histories retrieved", productService.listProductPurchaseHistories(productId));
    }
}
