package com.ada.purchase.factories.strategies;

import org.springframework.stereotype.Service;

import com.ada.purchase.entities.Purchase;
import com.ada.purchase.entities.enums.Method;
import com.ada.purchase.entities.enums.Status;
import com.ada.purchase.factories.PurchaseStrategy;
import com.ada.purchase.mappers.PurchaseMapper;
import com.ada.purchase.payloads.response.ResponsePurchaseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CashPurchaseStrategy implements PurchaseStrategy {

  @Override
  public Method getMethod() {
    return Method.CASH;
  }

  @Override
  public ResponsePurchaseDto pay(Purchase purchase) {

    System.out.println("Purchase with cash");

    purchase.setStatus(Status.APPROVED);

    return PurchaseMapper.INSTANCE.toDto(purchase);
  }

}
