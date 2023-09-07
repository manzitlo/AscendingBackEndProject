package org.ascending.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "universities")
@Getter
@Setter
public class University {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String location;
    @Column
    private String description;
    @Column
    private long ranking;
    @Column
    private String website;

    public University() {}

    public University(long id, String name, String location, String description, long ranking, String website) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.ranking = ranking;
        this.website = website;
    }

    @OneToMany(mappedBy = "university", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Question> questions;

}
