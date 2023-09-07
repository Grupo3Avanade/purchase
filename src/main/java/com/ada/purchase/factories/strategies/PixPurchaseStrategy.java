package com.ada.purchase.factories.strategies;

import org.springframework.stereotype.Service;

import com.ada.purchase.entities.Pix;
import com.ada.purchase.entities.Purchase;
import com.ada.purchase.entities.enums.Method;
import com.ada.purchase.entities.enums.Status;
import com.ada.purchase.factories.PurchaseStrategy;
import com.ada.purchase.mappers.PurchaseMapper;
import com.ada.purchase.payloads.response.ResponsePurchaseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PixPurchaseStrategy implements PurchaseStrategy {

  @Override
  public Method getMethod() {
    return Method.PIX;
  }

  @Override
  public ResponsePurchaseDto pay(Purchase purchase) {
    if (purchase.getPix() == null) {
      throw new RuntimeException("Pix purchase must have a pix");
    }

    Status status = isPixApproved(purchase.getPix()) ? Status.APPROVED : Status.REJECTED;

    purchase.setStatus(status);

    return PurchaseMapper.INSTANCE.toDto(purchase);
  }

  private boolean isPixApproved(Pix pix) {
    System.out.println("Check pix with external service");

    return true;
  }

}
