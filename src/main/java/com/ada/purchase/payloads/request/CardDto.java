package com.ada.purchase.payloads.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {
        @NotNull
        String holder;
        @NotNull
        String securityCode;
        @NotNull
        Integer expiryYear;
        @NotNull
        Integer expiryMonth;
        @NotNull
        String number;
}
