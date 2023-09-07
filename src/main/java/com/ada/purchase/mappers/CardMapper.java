package com.ada.purchase.mappers;

import java.math.BigDecimal;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ada.purchase.entities.Card;
import com.ada.purchase.entities.Pix;
import com.ada.purchase.entities.Purchase;
import com.ada.purchase.payloads.rabbitmq.CheckCardDto;
import com.ada.purchase.payloads.request.CardDto;
import com.ada.purchase.payloads.request.CreatePurchaseDto;
import com.ada.purchase.payloads.request.PixDto;
import com.ada.purchase.payloads.response.ResponsePurchaseDto;

@Mapper
public interface CardMapper {
  CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

  CheckCardDto toCheckCard(Card dto, BigDecimal amount);
}
