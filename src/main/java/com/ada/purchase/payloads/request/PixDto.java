package com.ada.purchase.payloads.request;

import com.ada.purchase.entities.enums.PixType;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PixDto {

        @NotNull
        String from;

        @NotNull
        String to;

        @NotNull
        String description;

        @NotNull
        PixType type;

}
