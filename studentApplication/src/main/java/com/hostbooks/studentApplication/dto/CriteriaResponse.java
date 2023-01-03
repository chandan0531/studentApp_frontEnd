package com.hostbooks.studentApplication.dto;

import com.hostbooks.studentApplication.entities.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CriteriaResponse {

    private List<Course> content;

    private Long totalPage;
}
