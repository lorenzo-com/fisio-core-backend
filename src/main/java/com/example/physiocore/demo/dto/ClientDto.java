package com.example.physiocore.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ClientDto {
    private Long id;
    private String dni;
    private String name;
    private String surname;
    private String birthDate;
    private String address;
    private String username;
    private String phone;
}
