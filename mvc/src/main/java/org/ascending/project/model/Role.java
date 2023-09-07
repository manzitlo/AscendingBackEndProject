package org.ascending.project.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "allowed_resource")
    @JsonIgnore
    private String allowedResource;
    @Column(name = "allowed_read")
    @JsonIgnore
    private Boolean allowedRead;
    @Column(name = "allowed_create")
    @JsonIgnore
    private Boolean allowedCreate;
    @Column(name = "allowed_update")
    @JsonIgnore
    private Boolean allowedUpdate;
    @Column(name = "allowed_delete")
    @JsonIgnore
    private Boolean allowedDelete;
    @Column(name = "allowed_upload")
    @JsonIgnore
    private Boolean allowedUpload;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles",  cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//    @JsonManagedReference
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setAllowedRead(boolean allowedRead) {
        this.allowedRead = allowedRead;
    }
    public boolean isAllowedRead() {
        return allowedRead;
    }
    public void setAllowedCreate(boolean allowedCreate) {
        this.allowedCreate = allowedCreate;
    }
    public boolean isAllowedCreate() {
        return allowedCreate;
    }
    public void setAllowedUpdate(boolean allowedUpdate) {
        this.allowedUpdate = allowedUpdate;
    }
    public boolean isAllowedUpdate() {
        return allowedUpdate;
    }
    public void setAllowedDelete(boolean allowedDelete) {
        this.allowedDelete = allowedDelete;
    }
    public boolean isAllowedDelete() {
        return allowedDelete;
    }

    public void setAllowedUpload(boolean allowedUpload) {
        this.allowedUpload = allowedUpload;
    }
    public boolean isAllowedUpload() {
        return allowedUpload;
    }


}
