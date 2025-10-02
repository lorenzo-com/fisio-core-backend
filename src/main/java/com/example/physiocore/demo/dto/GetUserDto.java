package com.example.physiocore.demo.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class GetUserDto {
	private Long id;
	private String name;
	private String surname;
	private String username;
	private Set<String> roles;
}