package edu.cscc.entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rentals")

public class Rentals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "rental_type")
    private String rentalType;

    @Column(name = "category")
    private String category;

    @Column(name = "year")
    private String year;

    @Column(name = "director")
    private String director;

    @Column(name = "num_available")
    private String numAvailable;


    @OneToMany(mappedBy = "rental")
    private List<OrderLineItems> orderLineItems;

    public Rentals() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRentalType() {
        return rentalType;
    }

    public void setRentalType(String rentalType) {
        this.rentalType = rentalType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getNumAvailable() {
        return numAvailable;
    }

    public void setNumAvailable(String numAvailable) {
        this.numAvailable = numAvailable;
    }

    public List<OrderLineItems> getOrderLineItems() {
        return orderLineItems;
    }

    public void setOrderLineItems(List<OrderLineItems> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }
}
