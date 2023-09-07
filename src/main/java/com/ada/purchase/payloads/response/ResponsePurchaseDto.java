package com.ada.purchase.payloads.response;

import java.math.BigDecimal;
import java.util.UUID;

import com.ada.purchase.entities.enums.Status;

import lombok.Builder;

@Builder
public record ResponsePurchaseDto(
    UUID cardId,
    String store,
    BigDecimal amount,
    Status status) {

}
