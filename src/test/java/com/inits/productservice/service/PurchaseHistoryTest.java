package com.inits.productservice.service;

import com.inits.productservice.constants.PurchaseStatus;
import com.inits.productservice.model.PurchaseHistory;
import com.inits.productservice.repository.PurchaseRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by Odinaka Onah on 03 Feb, 2021.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseHistoryTest {
    @MockBean
    private PurchaseRepository purchaseRepository;
    @Autowired
    private PurchaseService purchaseService;
    private String id = "51ba9483-c4cf-44b4-b0ac-ee8fb524c125";

    @Before
    public void setUp() {
        PurchaseHistory purchaseHistory = PurchaseHistory.builder().id(id).quantity(10).status(PurchaseStatus.PENDING.name()).totalAmount(10000).createdAt(LocalDateTime.now()).build();

        Mockito.when(purchaseRepository.findById(purchaseHistory.getId()))
                .thenReturn(java.util.Optional.of(purchaseHistory));
    }

    @Test
    public void testFindById() {
        var history = purchaseService.findById(id);
        System.out.println("History "+history);
        Assertions.assertThat(history.getId()).isEqualTo(id);
    }
}
