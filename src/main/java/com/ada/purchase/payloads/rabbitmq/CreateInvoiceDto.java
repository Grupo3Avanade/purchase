package com.ada.purchase.payloads.rabbitmq;

import java.math.BigDecimal;
import java.util.UUID;

import com.ada.purchase.entities.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceDto {
  UUID id;
  BigDecimal amount;
  Status status;
  String createdAt;
  String store;
  UUID cardId;
}
