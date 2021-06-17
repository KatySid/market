package ru.geekbrains.my.market.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn (name ="product_id")
    private Product product;

    @Column (name = "quantity")
    private int quantity;

    @Column (name = "price_per_product")
    private BigDecimal pricePerProduct;

    @Column (name = "price")
    private BigDecimal price;

    @Column (name ="created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


    @Column (name ="updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;


}