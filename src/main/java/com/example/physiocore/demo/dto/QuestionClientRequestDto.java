package com.example.physiocore.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionClientRequestDto {
    private String name;
    private String surname;
    private String username;
    private String phone;
    private String comment;
}
