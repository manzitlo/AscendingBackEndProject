package org.ascending.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car {

    public Car() {}

    public Car(long id, String brand, long year, String model, String color, long insuranceId) {
        this.id = id;
        this.brand = brand;
        this.year = year;
        this.model = model;
        this.color = color;
        this.insuranceId = insuranceId;
    }


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

    @OneToMany(mappedBy = "car", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Customer> customers;

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }
    public String getBrand() {
        return brand;
    }
    public void setYear(long year){
        this.year = year;
    }
    public long getYear(){
        return year;
    }
    public void setModel(String model){
        this.model = model;
    }
    public String getModel(){
        return model;
    }
    public void setColor(String color){
        this.color = color;
    }
    public String getColor(){
        return color;
    }

    public void setInsuranceId(long insurance_id){
        this.insuranceId = insurance_id;
    }
    public long getInsuranceId(){
        return insuranceId;
    }
    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
}
