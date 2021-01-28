package edu.cscc.entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_line_items")

public class OrderLineItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    private Orders order;

    @ManyToOne()
    private Rentals rental;

    public OrderLineItems() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Rentals getRental() {
        return rental;
    }

    public void setRental(Rentals rental) {
        this.rental = rental;
    }
}
