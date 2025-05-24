package com.quickbite.backend.model;

import com.quickbite.backend.model.enums.OrderStatus;
import com.quickbite.backend.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private double totalAmount;
    private PaymentStatus paymentStatus;
    private OrderStatus orderStatus;
    private LocalDateTime placedAt;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItems> items = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

