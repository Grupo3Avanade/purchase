package com.ada.purchase.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ada.purchase.payloads.request.CreatePurchaseDto;
import com.ada.purchase.payloads.response.ResponsePurchaseDto;
import com.ada.purchase.services.PurchaseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchaseController {
  private final PurchaseService service;

  @PostMapping
  public ResponsePurchaseDto create(@Valid @RequestBody CreatePurchaseDto dto) {
    return service.create(dto);
  }
}
