package com.ada.purchase.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ada.purchase.payloads.request.CreatePurchaseDto;
import com.ada.purchase.services.PurchaseService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PurchaseConsumer {
  private final PurchaseService service;

  @RabbitListener(queues = "${business.in.create-purchase}")
  public void consume(@Payload CreatePurchaseDto dto) {
    service.create(dto);
  }
}
