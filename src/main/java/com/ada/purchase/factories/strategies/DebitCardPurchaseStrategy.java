package com.ada.purchase.factories.strategies;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.purchase.configs.RestApi;
import com.ada.purchase.entities.Card;
import com.ada.purchase.entities.Purchase;
import com.ada.purchase.entities.enums.Method;
import com.ada.purchase.entities.enums.Status;
import com.ada.purchase.factories.PurchaseStrategy;
import com.ada.purchase.factories.strategies.dto.AutorizeDto;
import com.ada.purchase.factories.strategies.dto.AutorizeStatus;
import com.ada.purchase.mappers.CardMapper;
import com.ada.purchase.mappers.PurchaseMapper;
import com.ada.purchase.payloads.rabbitmq.CheckCardDto;
import com.ada.purchase.payloads.response.ResponsePurchaseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DebitCardPurchaseStrategy implements PurchaseStrategy {

  @Value("${business.out.check-debit-card}")
  private String queueName;

  @Override
  public Method getMethod() {
    return Method.DEBIT_CARD;
  }

  @Override
  public ResponsePurchaseDto pay(Purchase purchase) {
    if (purchase.getCard() == null) {
      throw new RuntimeException("Debit purchase must have a card");
    }

    Status status = isCardApproved(purchase.getCard(), purchase.getAmount());

    purchase.setStatus(status);

    return PurchaseMapper.INSTANCE.toDto(purchase);
  }

  private Status isCardApproved(Card card, BigDecimal amount) {
    try {
      System.out.println("Check Debit card with external service");

      CheckCardDto dto = CardMapper.INSTANCE.toCheckCard(card, amount);

      AutorizeStatus status = RestApi.card.post().uri("").bodyValue(dto).retrieve().bodyToMono(AutorizeDto.class)
          .block()
          .status();

      switch (status) {
        case APPROVED:
          return Status.APPROVED;
        case REJECTED:
          return Status.REJECTED;
        default:
          throw new RuntimeException("Invalid status");
      }
    } catch (Exception e) {
      return Status.PENDING;
    }
  }
}