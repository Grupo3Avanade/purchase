package com.ada.purchase.factories;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ada.purchase.entities.enums.Method;

@Component
public class PurchaseFactory {
  private final Map<Method, PurchaseStrategy> strategyMap;

  private PurchaseFactory(List<PurchaseStrategy> strategies) {
    this.strategyMap = strategies.stream()
        .collect(Collectors.toUnmodifiableMap(PurchaseStrategy::getMethod, Function.identity()));
  }

  public PurchaseStrategy getStrategy(Method method) {
    return strategyMap.get(method);
  }
}
