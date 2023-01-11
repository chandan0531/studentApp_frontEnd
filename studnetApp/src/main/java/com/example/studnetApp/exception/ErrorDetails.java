package com.example.studnetApp.exception;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ErrorDetails {

    private LocalDate localDate;

    private String message;

    private  String description;

}
