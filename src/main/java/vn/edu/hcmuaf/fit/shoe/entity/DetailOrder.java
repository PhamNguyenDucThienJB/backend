package vn.edu.hcmuaf.fit.shoe.entity;

import javax.persistence.*;

@Entity
@Table(name = "detail_order")
public class DetailOrder {
    @EmbeddedId
    private DetailOrderId id ;

    @Column(name = "quantity")
    private int quantity ;

    @ManyToOne
    @MapsId("orderId")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    public DetailOrder() {
    }

    public DetailOrder(DetailOrderId id, int quantity, Order order, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.order = order;
        this.product = product;
    }

    public DetailOrderId getId() {
        return id;
    }

    public void setId(DetailOrderId id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
