package com.ada.purchase.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ada.purchase.entities.Card;
import com.ada.purchase.entities.Pix;
import com.ada.purchase.entities.Purchase;
import com.ada.purchase.payloads.rabbitmq.CreateInvoiceDto;
import com.ada.purchase.payloads.request.CardDto;
import com.ada.purchase.payloads.request.CreatePurchaseDto;
import com.ada.purchase.payloads.request.PixDto;
import com.ada.purchase.payloads.response.ResponsePurchaseDto;

@Mapper
public interface PurchaseMapper {
  PurchaseMapper INSTANCE = Mappers.getMapper(PurchaseMapper.class);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "sharedId", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "status", ignore = true)
  Purchase toEntity(CreatePurchaseDto dto);

  @Mapping(target = "cardId", source = "card.id")
  @Mapping(target = "id", source = "sharedId")
  ResponsePurchaseDto toDto(Purchase entity);

  CreateInvoiceDto toCreateInvoiceDto(Purchase entity);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "purchase", ignore = true)
  Pix toPix(PixDto dto);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "purchase", ignore = true)
  Card toCard(CardDto dto);
}
