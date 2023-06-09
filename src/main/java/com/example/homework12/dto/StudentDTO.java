package com.example.homework12.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class StudentDTO {
    private Integer id;
    private String name;
    private String surname;
    private Integer level;
    private Integer age;
    private String gender;
    private LocalDateTime createDate;
    private LocalDate fromDate;
    private LocalDate toDate;

}
