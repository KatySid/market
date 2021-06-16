package ru.geekbrains.my.market.models;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "order")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderItem> items;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "adress")
    private String adress;

    @Column(name = "phone_Number")
    private String phoneNumber;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Order() {
        this.items = new ArrayList<>();
        this.price = BigDecimal.ZERO;
    }
}