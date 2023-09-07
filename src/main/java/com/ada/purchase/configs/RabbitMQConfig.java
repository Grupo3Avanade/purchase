package com.ada.purchase.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class RabbitMQConfig {
  @Value("${business.out.check-credit-card}")
  private String queueNameCheckCreditCard;

  @Value("${business.out.check-debit-card}")
  private String queueNameCheckDebitCard;

  @Value("${business.out.create-invoice}")
  private String queueNameCreateInvoice;

  @Value("${business.in.create-purchase}")
  private String queueNameCreatePurchase;

  @Bean
  Queue queueCreatePurchase() {
    return new Queue(queueNameCreatePurchase, true);
  }

  @Bean
  Queue queueCheckCreditCard() {
    return new Queue(queueNameCheckCreditCard, true);
  }

  @Bean
  Queue queueCheckDebitCard() {
    return new Queue(queueNameCheckDebitCard, true);
  }

  @Bean
  Queue queueCreateInvoice() {
    return new Queue(queueNameCreateInvoice, true);
  }

  @Bean
  ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper;
  }

  @Bean
  Jackson2JsonMessageConverter producerJackson2MessageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
