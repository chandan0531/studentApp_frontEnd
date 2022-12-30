package com.hostbooks.studentApplication.dto;

import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentDto {

    private Integer studentId;

    //    @NotNull(message = "Name should not be null")
//    @Size(min=4,max=10)
    private String name;


    private String gender;

//    @Size(min=10,max=10)
    private String cellPhone;

    private String email;

    private  Integer courseId;

}
