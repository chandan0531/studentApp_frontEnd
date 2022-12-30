package com.hostbooks.studentApplication.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer addId;

    @Size(min = 2 ,message = "street size must be min 2 ")
    private String area;

    @Size(min = 2 ,message = "city size must be min 2 ")
    private String city;

    @Size(min = 2 ,message = "district size must be min 2 ")
    private String district;

    @Size(min = 2 ,message = "addressType size must be min 2 ")
    private String addressType;


    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @JsonIgnore
    private Student student;
}
