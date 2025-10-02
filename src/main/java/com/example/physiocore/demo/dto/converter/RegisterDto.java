package com.example.physiocore.demo.dto.converter;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterDto {
	private String name;
	private String surname;
    private String username;
	private String phone;
	private String password;
}
