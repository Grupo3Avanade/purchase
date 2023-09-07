package com.ada.purchase.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "pix")
@Entity(name = "pix")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pix {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "hash", nullable = false)
  private String hash;

  @OneToOne(mappedBy = "pix")
  private Purchase purchase;
}
