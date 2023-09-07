package com.ada.purchase.payloads.rabbitmq;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ada.purchase.entities.enums.Method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceDto {
  BigDecimal amount;
  LocalDateTime createdAt;
  String store;
  Method method;
}
