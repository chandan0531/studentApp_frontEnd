package com.hostbooks.studentApplication.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@org.hibernate.annotations.NamedQueries(
        {
                @org.hibernate.annotations.NamedQuery(
                        name = "findByCourseName",
                        query = "from Course c where c.courseName=:cname"
                )
        }
)



@Entity
@Getter
@Setter
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer courseId;

    @NotNull
    private String courseName;

    @NotNull
    private String description;

    @NotNull
    private String courseType;

    @NotNull
    private Integer duration;

    @NotNull
    private String topics;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "course",  orphanRemoval = true )
    @JsonIgnore
    private Student student;

}
