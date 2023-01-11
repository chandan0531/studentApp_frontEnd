package com.example.studnetApp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.text.FlowView;
import javax.validation.constraints.NotNull;


@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "student")
public class Student {

    @Id
    private Integer stdId;

    @NotNull(message = "enter first name")
    private String firstName;
    @NotNull(message = "enter last name")
    private String lastName;

    @NotNull(message = "enter email")
    private String emailId;
}
