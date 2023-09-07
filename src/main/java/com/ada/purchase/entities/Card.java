package com.ada.purchase.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "cards")
@Entity(name = "cards")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Card {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "holder", nullable = false)
  private String holder;

  @Column(name = "number", nullable = false)
  private String number;

  @Column(name = "expiry_year", nullable = false)
  private Integer expiryYear;

  @Column(name = "expiry_month", nullable = false)
  private Integer expiryMonth;

  @Column(name = "security_code", nullable = false)
  private String securityCode;

  @OneToMany(mappedBy = "card")
  private List<Purchase> purchases;
}
