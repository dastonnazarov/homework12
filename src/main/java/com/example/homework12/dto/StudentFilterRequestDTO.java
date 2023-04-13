package com.example.homework12.dto;


import com.example.homework12.enums.GenderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentFilterRequestDTO {
    private String name;
    private Integer level;
    private String gender;

}

