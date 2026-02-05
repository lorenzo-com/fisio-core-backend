package com.example.physiocore.demo.dto.converter;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class RegisterDto {
	private String dni;
	private String name;
	private String surname;
	private LocalDate birthDate;
	private String address;
    private String username;
	private String phone;
	private String password;
}
