package ru.geekbrains.my.market.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem implements Serializable {
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

    @Column(name = "adress")
    private String adress;

    @Column(name = "phone_Number")
    private String phoneNumber;

    @Column (name ="created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


    @Column (name ="updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public OrderItem (Product product){
        this.product = product;
        this.quantity = 1;
        this.pricePerProduct = product.getPrice();
        this.price = product.getPrice();
    }

    public void incrementQuantity(){
        this.quantity++;
        this.price = this.pricePerProduct.multiply(new BigDecimal(this.quantity));
    }

    public void decrementQuantity(){
        this.quantity--;
        this.price = this.pricePerProduct.multiply(new BigDecimal(this.quantity));
    }

}