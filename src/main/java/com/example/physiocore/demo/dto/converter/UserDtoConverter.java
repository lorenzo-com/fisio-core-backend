package com.example.physiocore.demo.dto.converter;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.physiocore.demo.dto.GetUserDto;
import com.example.physiocore.demo.model.Client;
import com.example.physiocore.demo.model.UserRole;

@Component
public class UserDtoConverter {
    public GetUserDto convertUserEntityToGetUserDto(Client user) {
		return GetUserDto.builder()
				.id(user.getId())
				.username(user.getUsername())
				.name(user.getName())
				.surname(user.getSurname())
				.roles(user.getRoles().stream()
							.map(UserRole::name)
							.collect(Collectors.toSet())
				)
				.build();
	}

	public Client convertRegisterDtoToClient(RegisterDto dto) {
		Client client = new Client();
		client.setName(dto.getName());
		client.setSurname(dto.getSurname());
		client.setUsername(dto.getUsername());
		client.setPassword(dto.getPassword());
		client.setPhone(dto.getPhone());
		client.getRoles().add(UserRole.PATIENT); // Default role for new users

		return client;
	}
}
