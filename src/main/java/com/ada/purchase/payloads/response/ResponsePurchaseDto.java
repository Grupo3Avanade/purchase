package com.ada.purchase.payloads.response;

import java.math.BigDecimal;
import java.util.UUID;

import com.ada.purchase.entities.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePurchaseDto {
    UUID id;
    UUID cardId;
    String store;
    BigDecimal amount;
    Status status;
}
