package org.ascending.project.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "questions")
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String content;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Question(){}


}
