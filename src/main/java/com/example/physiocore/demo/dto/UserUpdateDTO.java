package com.example.physiocore.demo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class UserUpdateDTO {
    private String name;
    private String surname;
    private String dni;
    private String address;
    private String phone;
    private LocalDate birthDate;
}
