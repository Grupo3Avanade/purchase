package com.ada.purchase.payloads.rabbitmq;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckCardDto {
  String holder;
  String securityCode;
  LocalDateTime expiry;
  String number;
  BigDecimal amount;
}
