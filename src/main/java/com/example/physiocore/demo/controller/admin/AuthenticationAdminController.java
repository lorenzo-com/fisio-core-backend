package com.example.physiocore.demo.controller.admin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.physiocore.demo.dto.converter.RegisterDto;
import com.example.physiocore.demo.dto.converter.UserDtoConverter;
import com.example.physiocore.demo.model.AppUser;
import com.example.physiocore.demo.model.UserRole;
import com.example.physiocore.demo.services.UserService;

@RestController
@RequestMapping("/admin")
public class AuthenticationAdminController {
    @Autowired
    private UserService adminService;
    @Autowired
    private UserDtoConverter userDtoConverter;

    @PostMapping("/register")
	public ResponseEntity<?> newAdmin(@RequestBody RegisterDto newUser) {
		if (adminService.findByUsername(newUser.getUsername()).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("EMAIL ALREADY EXISTS");
		}

		AppUser admin = userDtoConverter.convertRegisterDtoToUser(newUser, UserRole.ADMIN);

		userDtoConverter.convertUserEntityToGetUserDto(adminService.saveUser(admin));

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(Map.of("message", "Administrador registrado exitosamente."));
	}
}
