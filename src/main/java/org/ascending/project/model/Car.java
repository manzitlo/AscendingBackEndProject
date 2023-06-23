package org.ascending.project.model;

public class Car {

    public Car() {}
    private long id;
    private String brand;
    private Integer year;

    private String model;

    private String color;

    private long insurance_id;

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
        this.insurance_id = insurance_id;
    }

}
