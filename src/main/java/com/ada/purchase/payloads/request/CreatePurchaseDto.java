package com.ada.purchase.payloads.request;

import java.math.BigDecimal;

import com.ada.purchase.entities.enums.Method;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePurchaseDto {
        @NotNull
        Method method;
        CardDto card;
        PixDto pix;
        @NotNull
        String store;
        @NotNull
        BigDecimal amount;
}
