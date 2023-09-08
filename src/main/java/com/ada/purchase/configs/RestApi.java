package com.ada.purchase.configs;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.Getter;

@Component
public class RestApi {
  public static final WebClient card = WebClient.builder().baseUrl("https://run.mocky.io").build();
}
