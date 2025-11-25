package com.example.physiocore.demo.controller;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.physiocore.demo.dto.GetUserDto;
import com.example.physiocore.demo.dto.converter.RegisterDto;
import com.example.physiocore.demo.dto.converter.UserDtoConverter;
import com.example.physiocore.demo.model.Client;
import com.example.physiocore.demo.model.UserRole;
import com.example.physiocore.demo.repository.AppoinmentRepository;
import com.example.physiocore.demo.security.jwt.JwtProvider;
import com.example.physiocore.demo.security.jwt.model.JwtUserResponse;
import com.example.physiocore.demo.security.jwt.model.LoginRequest;
import com.example.physiocore.demo.services.ClientService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	private final AuthenticationManager authenticationManager;
	private final JwtProvider tokenProvider;
	private final UserDtoConverter converter;

	private final ClientService clientService;
	private final AppoinmentRepository appointmentRepository;
	private final UserDtoConverter userDtoConverter;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			Client principal = (Client) authentication.getPrincipal();

			String jwtToken = tokenProvider.generateToken(authentication);

			return ResponseEntity.status(HttpStatus.CREATED)
					.body(convertUserEntityAndTokenToJwtUserResponse(principal, jwtToken));

		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("INCORRECT CREDENTIALS");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("THERE WAS AN INTERNAL SERVER ERROR");
		}
	}

	private JwtUserResponse convertUserEntityAndTokenToJwtUserResponse(Client user, String jwtToken) {
		return JwtUserResponse.jwtUserResponseBuilder()
				.username(user.getUsername())
				.roles(user.getRoles().stream().map(UserRole::name).collect(Collectors.toSet()))
				.token(jwtToken)
				.build();
	}

	@PostMapping("/register")
	public ResponseEntity<?> newUser(@RequestBody RegisterDto newUser) {
		System.out.println("Registering new user: " + newUser.getUsername());
		if (clientService.findByUsername(newUser.getUsername()).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("EMAIL ALREADY EXISTS");
		}

		Client client = userDtoConverter.convertRegisterDtoToClient(newUser);

		userDtoConverter.convertUserEntityToGetUserDto(clientService.save(client));

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(Map.of("message", "The user has registered successfully."));
	}

	@GetMapping("/user/me")
	public GetUserDto me(@AuthenticationPrincipal Client user) {
		Long numAppoinments = appointmentRepository.countByPatientId(user.getId());
		return converter.convertUserEntityToGetUserDto(user, numAppoinments);
	}

	@GetMapping("/isAuthenticated")
	public ResponseEntity<Boolean> isAuthenticated(@AuthenticationPrincipal Client user) {
		return ResponseEntity.ok(user != null);
	}
}
