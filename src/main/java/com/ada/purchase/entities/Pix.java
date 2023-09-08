package com.ada.purchase.entities;

import java.util.UUID;

import com.ada.purchase.entities.enums.PixType;

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

  @Column(name = "from", nullable = false)
  private String from;

  @Column(name = "to", nullable = false)
  private String to;

  @Column(name = "description", nullable = false)
  private String description;
  
  @Column(name = "type", nullable = false)
  private PixType type;

  @OneToOne(mappedBy = "pix")
  private Purchase purchase;
}
