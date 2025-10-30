package com.example.physiocore.demo.security.jwt.model;

import java.time.LocalDate;
import java.util.Set;

import com.example.physiocore.demo.dto.GetUserDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtUserResponse extends GetUserDto {

	private String token;
	
	@Builder(builderMethodName="jwtUserResponseBuilder")
	public JwtUserResponse(Long id, String name, String surname, String username, String phone, String address, LocalDate birthDate, Set<String> roles, String token) {
		super(id, phone, birthDate, address, username, name, surname, roles);
		this.token = token;
	}
}