package org.ascending.project.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

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
    private Date transaction_date;

//    default CURRENT_TIME  in SQL
//    @Column(name = "car_id")
//    private long carId;



    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public void setCustomerId (long customer_id){
        this.customer_id = customer_id;
    }
    public long getCustomerId() {
        return customer_id;
    }
    public void setFirstName (String first_name){
        this.first_name = first_name;
    }

    public String getFirst_name() {
        return first_name;
    }
    public void setLastName (String last_name){
        this.last_name = last_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setAge (Integer age){
        this.age = age;
    }
    public long getAge() {
        return age;
    }
    public void setGender (String gender){
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }
    public void setTransactionDate (Date transaction_date){
        this.transaction_date = transaction_date;
    }
    public Date getTransactionDate() {
        return transaction_date;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Car getCar() {  //*******
        return car;
    }


    @Override
    public int hashCode(){

//        final int prime = 31;
//        int result = 1;
//        result = prime * result + (int)customer_id;
//        result = prime * result + first_name.hashCode();
//        result = prime * result + last_name.hashCode();
//        result = prime * result + gender.hashCode();
//        result = prime * result + transaction_date.hashCode();
//        result = prime * result + (int)age;
//        return result;

        return Objects.hash(customer_id,first_name,last_name,gender,transaction_date,age);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;
        return customer_id == customer.customer_id &&
                first_name.equals(customer.first_name) &&
                last_name.equals(customer.last_name) &&
                age == customer.age &&
                transaction_date.equals(customer.transaction_date) &&
                gender.equals(customer.gender);
    }
}
