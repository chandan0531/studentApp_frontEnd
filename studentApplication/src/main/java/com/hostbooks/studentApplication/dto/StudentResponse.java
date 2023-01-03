package com.hostbooks.studentApplication.dto;


import com.hostbooks.studentApplication.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {

    private List<Student> content;

    private Long totalPage;
}
