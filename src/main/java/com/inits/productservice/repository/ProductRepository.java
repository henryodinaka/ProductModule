package com.inits.productservice.repository;

import com.inits.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Odinaka Onah on 02 Feb, 2021.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

}
