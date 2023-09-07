package com.ada.purchase.factories;

import com.ada.purchase.entities.Purchase;
import com.ada.purchase.entities.enums.Method;
import com.ada.purchase.payloads.response.ResponsePurchaseDto;

public interface PurchaseStrategy {
  Method getMethod();
  ResponsePurchaseDto pay(Purchase purchase);
}
