package org.ascending.project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "events")
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column
    private String title;
    @Column
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column
    private String time;
    @Column
    private String location;
    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private User user;

}
