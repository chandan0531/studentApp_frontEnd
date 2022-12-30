package com.hostbooks.studentApplication.exception;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MyError {

    private LocalDate localDate;

    private String message;

    private String description;

}
