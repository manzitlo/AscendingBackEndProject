package org.ascending.project.model;

public class Insurance {
    public Insurance() {}

    private long id;
    private String company_name;

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
