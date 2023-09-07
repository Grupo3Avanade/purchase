package com.ada.purchase.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ada.purchase.entities.Purchase;
import com.ada.purchase.entities.enums.Status;
import com.ada.purchase.factories.PurchaseFactory;
import com.ada.purchase.factories.PurchaseStrategy;
import com.ada.purchase.mappers.PurchaseMapper;
import com.ada.purchase.payloads.rabbitmq.CreateInvoiceDto;
import com.ada.purchase.payloads.request.CreatePurchaseDto;
import com.ada.purchase.payloads.response.ResponsePurchaseDto;
import com.ada.purchase.repositories.PurchaseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseService {
  private final PurchaseRepository repository;
  private final PurchaseFactory purchaseFactory;

  @Value("${business.out.create-invoice}")
  private String queueName;

  private final RabbitTemplate rabbitTemplate;
  private final ObjectMapper objectMapper;

  public ResponsePurchaseDto create(CreatePurchaseDto dto) {
    Purchase purchase = PurchaseMapper.INSTANCE.toEntity(dto);

    PurchaseStrategy strategy = purchaseFactory.getStrategy(dto.getMethod());

    strategy.pay(purchase);
    generateInvoice(purchase);

    Purchase result = repository.save(purchase);

    return PurchaseMapper.INSTANCE.toDto(result);
  }

  private void generateInvoice(Purchase purchase) {
    try {
      CreateInvoiceDto dto = PurchaseMapper.INSTANCE.toCreateInvoiceDto(purchase);
      rabbitTemplate.convertAndSend(queueName, objectMapper.writeValueAsString(dto));
    } catch (Exception e) {
      purchase.setStatus(Status.INVOICE_PENDING);
    } 
  }
}
