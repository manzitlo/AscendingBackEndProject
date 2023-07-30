package org.ascending.project.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "name")
    private String name;

    public String getAllowedResource() {
        return allowedResource;
    }

    public void setAllowedResource(String allowedResource) {
        this.allowedResource = allowedResource;
    }

    @Column(name = "allowed_resource")
    private String allowedResource;

    public Boolean getAllowedRead() {
        return allowedRead;
    }

    public void setAllowedRead(Boolean allowedRead) {
        this.allowedRead = allowedRead;
    }

    @Column(name = "allowed_read")
    private Boolean allowedRead;

    public Boolean getAllowedCreate() {
        return allowedCreate;
    }

    public void setAllowedCreate(Boolean allowedCreate) {
        this.allowedCreate = allowedCreate;
    }

    @Column(name = "allowed_create")
    private Boolean allowedCreate;

    public Boolean getAllowedUpdate() {
        return allowedUpdate;
    }

    public void setAllowedUpdate(Boolean allowedUpdate) {
        this.allowedUpdate = allowedUpdate;
    }

    @Column(name = "allowed_update")
    private Boolean allowedUpdate;

    public Boolean getAllowedDelete() {
        return allowedDelete;
    }

    public void setAllowedDelete(Boolean allowedDelete) {
        this.allowedDelete = allowedDelete;
    }

    @Column(name = "allowed_delete")
    private Boolean allowedDelete;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

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
