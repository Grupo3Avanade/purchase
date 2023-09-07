package com.ada.purchase.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ada.purchase.entities.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {
  
}
