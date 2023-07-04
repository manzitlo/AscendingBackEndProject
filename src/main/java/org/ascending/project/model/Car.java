package org.ascending.project.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car {

    public Car() {}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "brand")
    private String brand;
    @Column(name = "year")
    private long year;
    @Column(name = "model")
    private String model;
    @Column(name = "color")
    private String color;
    @Column(name = "insurance_id")
    private long insuranceId;

    @OneToMany(mappedBy = "car", cascade = CascadeType.REMOVE)
    private List<Customer> customers;

    public void setId(long id) {
        this.id = id;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }
    public void setYear(Integer year){
        this.year = year;
    }
    public void setModel(String model){
        this.model = model;
    }
    public void setColor(String color){
        this.color = color;
    }

    public void setInsuranceId(long insurance_id){
        this.insuranceId = insurance_id;
    }
    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
