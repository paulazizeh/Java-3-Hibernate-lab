package edu.cscc.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employees")

public class Employees {

        @Id
        private Integer id;

        @Column(name = "first_name")
        private String firstName;

        @Column(name = "last_name")
        private String lastName;

        @Column(name = "active_store_number")
        private String activeStoreNumber;

        @OneToMany(mappedBy = "employee")
        private List<Orders> orders;

        public Employees() {
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getActiveStoreNumber() {
                return activeStoreNumber;
        }

        public void setActiveStoreNumber(String activeStoreNumber) {
                this.activeStoreNumber = activeStoreNumber;
        }

        public List<Orders> getOrders() {
                return orders;
        }

        public void setOrders(List<Orders> orders) {
                this.orders = orders;
        }
}
