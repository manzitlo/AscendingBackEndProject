package org.ascending.project.model;

import javax.persistence.*;

@Entity
@Table(name = "insurances")
public class Insurance {
    public Insurance() {}

    @Id  //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "sepcifications")
    private String sepcifications;

//    @ManyToOne
//    @JoinColumn(name = "car_id")
//    private Car car;

    public void setId(long id){
        this.id = id;
    }
    public long getId(){
        return id;
    }
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setSepcifications(String sepcifications){
        this.sepcifications = sepcifications;
    }
    public String getSepcifications() {
        return sepcifications;
    }


}
