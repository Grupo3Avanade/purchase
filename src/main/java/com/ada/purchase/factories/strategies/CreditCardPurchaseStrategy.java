package com.ada.purchase.factories.strategies;

import java.math.BigDecimal;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.purchase.entities.Card;
import com.ada.purchase.entities.Purchase;
import com.ada.purchase.entities.enums.Method;
import com.ada.purchase.entities.enums.Status;
import com.ada.purchase.factories.PurchaseStrategy;
import com.ada.purchase.mappers.CardMapper;
import com.ada.purchase.mappers.PurchaseMapper;
import com.ada.purchase.payloads.rabbitmq.CheckCardDto;
import com.ada.purchase.payloads.response.ResponsePurchaseDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditCardPurchaseStrategy implements PurchaseStrategy {

  @Value("${business.out.check-credit-card}")
  private String queueName;

  private final RabbitTemplate rabbitTemplate;
  private final ObjectMapper objectMapper;

  @Override
  public Method getMethod() {
    return Method.CREDIT_CARD;
  }

  @Override
  public ResponsePurchaseDto pay(Purchase purchase) {
    if (purchase.getCard() == null) {
      throw new RuntimeException("Credit purchase must have a card");
    }

    Status status = isCardApproved(purchase.getCard(), purchase.getAmount()) ? Status.APPROVED : Status.REJECTED;

    purchase.setStatus(status);

    return PurchaseMapper.INSTANCE.toDto(purchase);
  }

  private boolean isCardApproved(Card card, BigDecimal amount) {
    try {
      System.out.println("Check Credit card with external service");

      CheckCardDto dto = CardMapper.INSTANCE.toCheckCard(card, amount);

      rabbitTemplate.convertAndSend(queueName, objectMapper.writeValueAsString(dto));

      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

}
