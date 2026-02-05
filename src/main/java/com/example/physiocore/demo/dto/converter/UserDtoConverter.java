package com.example.physiocore.demo.dto.converter;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.physiocore.demo.dto.GetUserDto;
import com.example.physiocore.demo.model.AppUser;
import com.example.physiocore.demo.model.UserRole;

@Component
public class UserDtoConverter {
	public GetUserDto convertUserEntityToGetUserDto(AppUser user, Long numAppoinments) {
		return GetUserDto.builder()
				.id(user.getId())
				.dni(user.getDni())
				.phone(user.getPhone())
				.birthDate(user.getBirthDate())
				.address(user.getAddress())
				.username(user.getUsername())
				.name(user.getName())
				.surname(user.getSurname())
				.numAppointments(numAppoinments)
				.roles(user.getRoles().stream()
							.map(UserRole::name)
							.collect(Collectors.toSet())
				)
				.build();
	}

    public GetUserDto convertUserEntityToGetUserDto(AppUser user) {
		return GetUserDto.builder()
				.id(user.getId())
				.phone(user.getPhone())
				.birthDate(user.getBirthDate())
				.address(user.getAddress())
				.dni(user.getDni())
				.username(user.getUsername())
				.name(user.getName())
				.surname(user.getSurname())
				.roles(user.getRoles().stream()
							.map(UserRole::name)
							.collect(Collectors.toSet())
				)
				.build();
	}

	public AppUser convertRegisterDtoToUser(RegisterDto dto, UserRole role) {
		AppUser user = new AppUser();
		user.setName(dto.getName());
		user.setSurname(dto.getSurname());
		user.setBirthDate(dto.getBirthDate());
		user.setAddress(dto.getAddress());
		user.setUsername(dto.getUsername());
		user.setPhone(dto.getPhone());
		user.setDni(dto.getDni());
		user.setPassword(dto.getPassword());
		user.getRoles().add(role);

		return user;
	}
}
