package com.inits.productservice.repository;

import com.inits.productservice.model.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Odinaka Onah on 02 Feb, 2021.
 */
@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseHistory,String> {
}
