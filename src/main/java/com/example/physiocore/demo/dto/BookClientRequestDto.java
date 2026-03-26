package com.example.physiocore.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookClientRequestDto {
    private String name;
    private String username;
    private String phone;
    private String date;
    private String hour;
    private String service;
}
