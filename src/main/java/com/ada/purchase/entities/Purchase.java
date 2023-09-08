package com.ada.purchase.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.ada.purchase.entities.enums.Method;
import com.ada.purchase.entities.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "purchases")
@Entity(name = "purchases")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Purchase {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "shared_id", nullable = false, updatable = false)
  private UUID sharedId = UUID.randomUUID();

  @Column(name = "amount", nullable = false, precision = 10, scale = 2)
  private BigDecimal amount;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private Status status = Status.PENDING;

  @Column(name = "created_at", nullable = false, updatable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @Column(name = "store", nullable = false)
  private String store;

  @Enumerated(EnumType.STRING)
  @Column(name = "method", nullable = false)
  private Method method;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "card_id")
  private Card card;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "pix_id")
  private Pix pix;
}
