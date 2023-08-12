package org.ascending.project.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "allowed_resource")
    private String allowedResource;
    @Column(name = "allowed_read")
    private Boolean allowedRead;
    @Column(name = "allowed_create")
    private Boolean allowedCreate;
    @Column(name = "allowed_update")
    private Boolean allowedUpdate;
    @Column(name = "allowed_delete")
    private Boolean allowedDelete;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAllowedResource() {
        return allowedResource;
    }
    public void setAllowedResource(String allowedResource) {
        this.allowedResource = allowedResource;
    }
    public Boolean getAllowedRead() {
        return allowedRead;
    }
    public void setAllowedRead(Boolean allowedRead) {
        this.allowedRead = allowedRead;
    }
    public Boolean getAllowedCreate() {
        return allowedCreate;
    }
    public void setAllowedCreate(Boolean allowedCreate) {
        this.allowedCreate = allowedCreate;
    }
    public Boolean getAllowedUpdate() {
        return allowedUpdate;
    }
    public void setAllowedUpdate(Boolean allowedUpdate) {
        this.allowedUpdate = allowedUpdate;
    }

    public Boolean getAllowedDelete() {
        return allowedDelete;
    }

    public void setAllowedDelete(Boolean allowedDelete) {
        this.allowedDelete = allowedDelete;
    }

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

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
}
