package com.ada.purchase.payloads.rabbitmq;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckCardDto {
  String holder;
  String securityCode;
  Integer expiryYear;
  Integer expiryMonth;
  String number;
  BigDecimal amount;
}
