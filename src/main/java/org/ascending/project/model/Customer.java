package org.ascending.project.model;

import java.sql.Date;

public class Customer {
    public Customer() {}

    private long customer_id;

    private String first_name;

    private String last_name;

    private Integer age;

    private String gender;

    private Date transaction_date;  // default CURRENT_TIME  in SQL

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
