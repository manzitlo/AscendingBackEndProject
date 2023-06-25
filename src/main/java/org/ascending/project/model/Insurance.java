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
    private String company_name;
    @Column(name = "Sepcifications")
    private String Sepcifications;

    public void setId(long id){
        this.id = id;
    }
    public void setCompanyName(String company_name){
        this.company_name = company_name;
    }
    public void setSepcifications(String Sepcifications){
        this.Sepcifications = Sepcifications;
    }


}
