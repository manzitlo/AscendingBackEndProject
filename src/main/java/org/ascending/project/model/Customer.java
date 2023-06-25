package org.ascending.project.model;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "customers")
public class Customer {

    public Customer() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long customer_id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "age")
    private long age;
    @Column(name = "gender")
    private String gender;
    @Column(name = "transaction_date")
    private Date transaction_date;  // default CURRENT_TIME  in SQL
    @Column(name = "car_id")
    private long car_id;

    public void setCustomerId (long customer_id){
        this.customer_id = customer_id;
    }
    public void setFirstName (String first_name){
        this.first_name = first_name;
    }

    public void setLastName (String last_name){
        this.last_name = last_name;
    }

    public void setAge (Integer age){
        this.age = age;
    }

    public void setGender (String gender){
        this.gender = gender;
    }
    public void setTransactionDate (Date transaction_date){
        this.transaction_date = transaction_date;
    }

    public void setCarId (long car_id){
        this.car_id = car_id;
    }
}
