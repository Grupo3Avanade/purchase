package com.ada.purchase.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.purchase.entities.Purchase;
import com.ada.purchase.factories.PurchaseFactory;
import com.ada.purchase.factories.PurchaseStrategy;
import com.ada.purchase.mappers.PurchaseMapper;
import com.ada.purchase.payloads.request.CreatePurchaseDto;
import com.ada.purchase.payloads.response.ResponsePurchaseDto;
import com.ada.purchase.repositories.PurchaseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseService {
  private final PurchaseRepository repository;
  private final PurchaseFactory purchaseFactory;

  @Value("${business.out.create-invoice}")
  private String queueName;

  public ResponsePurchaseDto create(CreatePurchaseDto dto) {
    Purchase purchase = PurchaseMapper.INSTANCE.toEntity(dto);

    PurchaseStrategy strategy = purchaseFactory.getStrategy(dto.getMethod());

    strategy.pay(purchase);

    Purchase result = repository.save(purchase);

    return PurchaseMapper.INSTANCE.toDto(result);
  }

}
