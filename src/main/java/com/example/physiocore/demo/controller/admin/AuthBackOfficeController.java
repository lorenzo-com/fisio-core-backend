package com.example.physiocore.demo.controller.admin;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.physiocore.demo.model.AppUser;
import com.example.physiocore.demo.model.UserRole;
import com.example.physiocore.demo.security.jwt.JwtProvider;
import com.example.physiocore.demo.security.jwt.model.JwtUserResponse;
import com.example.physiocore.demo.security.jwt.model.LoginRequest;
import com.example.physiocore.demo.services.UserService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/backoffice/auth")
public class AuthBackOfficeController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider tokenProvider;
    @Autowired
    private UserDtoConverter converter;

    @Autowired
    private UserService adminService;
    @Autowired
    private UserDtoConverter userDtoConverter;

    @PostMapping("/login")
    
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            AppUser principal = (AppUser) authentication.getPrincipal();

            boolean hasAccess = principal.getRoles().contains(UserRole.ADMIN)
                    || principal.getRoles().contains(UserRole.PROFESSIONAL);
            if (!hasAccess) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(Map.of("message", "No tienes permisos para acceder a esta área."));
            }

            // Validar si el usuario está activo
            boolean isActive = Boolean.TRUE.equals(principal.getActive());
            if (!isActive) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message", "Tu cuenta está inactiva."));
            }

            String jwtToken = tokenProvider.generateToken(authentication);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(convertUserEntityAndTokenToJwtUserResponse(principal, jwtToken));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message",
                    "Credenciales inválidas. Por favor, verifica tu nombre de usuario y contraseña."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("THERE WAS AN INTERNAL SERVER ERROR");
        }
    }

    private JwtUserResponse convertUserEntityAndTokenToJwtUserResponse(AppUser user, String jwtToken) {
        return JwtUserResponse.jwtUserResponseBuilder()
                .roles(user.getRoles().stream().map(UserRole::name).collect(Collectors.toSet()))
                .token(jwtToken)
                .build();
    }

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

    @GetMapping("/user/me")
    public GetUserDto me(@AuthenticationPrincipal AppUser user) {
        return converter.convertUserEntityToGetUserDto(user);
    }
}
