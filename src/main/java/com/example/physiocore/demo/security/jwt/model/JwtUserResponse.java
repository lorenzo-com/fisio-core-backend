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
	public JwtUserResponse(Long id, String dni, String phone, LocalDate birthDate, String address, String surname, String name, String username, Set<String> roles, Long numAppointments, String token) {
		super(id, dni, phone, birthDate, address, username, name, surname, roles, numAppointments);
		this.token = token;
	}
}