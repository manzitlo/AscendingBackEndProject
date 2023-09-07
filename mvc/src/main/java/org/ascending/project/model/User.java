package org.ascending.project.model;
import com.fasterxml.jackson.annotation.*;
import org.apache.commons.codec.digest.DigestUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIgnoreProperties({"profilePicture", "bio", "location", "roles"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "profile_picture")
    private String profilePicture;
    @Column
    private String bio;
    @Column
    private String location;

    public void setPassword(String password) {
        this.password = DigestUtils.md5Hex(password.trim());
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Question> questions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Answer> answers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Event> events;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
//    @JsonManagedReference
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }
}