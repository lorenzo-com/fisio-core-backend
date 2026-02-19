package com.example.physiocore.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.physiocore.demo.dto.UserDto;
import com.example.physiocore.demo.dto.UserUpdateDTO;
import com.example.physiocore.demo.model.AppUser;
import com.example.physiocore.demo.model.UserRole;
import com.example.physiocore.demo.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUser updateClient(Long id, UserUpdateDTO clientData) {
        AppUser clientToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        clientToUpdate.setName(clientData.getName());
        clientToUpdate.setSurname(clientData.getSurname());
        clientToUpdate.setDni(clientData.getDni());
        clientToUpdate.setAddress(clientData.getAddress());
        clientToUpdate.setPhone(clientData.getPhone());
        clientToUpdate.setBirthDate(clientData.getBirthDate());

        return userRepository.save(clientToUpdate);
    }

    public Optional<AppUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<UserDto> getClientById(Long id) {
        return userRepository.findById(id)
                .map(client -> UserDto.builder()
                        .id(client.getId())
                        .dni(client.getDni())
                        .name(client.getName())
                        .surname(client.getSurname())
                        .birthDate(client.getBirthDate() != null ? client.getBirthDate().toString() : null)
                        .address(client.getAddress())
                        .username(client.getUsername())
                        .phone(client.getPhone())
                        .build());
    }

    public List<UserDto> getAllClients() {
        List<AppUser> clients = userRepository.findAllByRole(UserRole.PATIENT);

        return clients.stream()
                .map(client -> UserDto.builder()
                        .id(client.getId())
                        .dni(client.getDni())
                        .name(client.getName())
                        .surname(client.getSurname())
                        .birthDate(client.getBirthDate() != null ? client.getBirthDate().toString() : null)
                        .address(client.getAddress())
                        .username(client.getUsername())
                        .phone(client.getPhone())
                        .build())
                .toList();
    }

    public List<UserDto> getAllProfessionals() {
        List<AppUser> professionals = userRepository.findAllByRole(UserRole.PROFESSIONAL);
        return professionals.stream()
                .map(professional -> UserDto.builder()
                        .id(professional.getId())
                        .dni(professional.getDni())
                        .name(professional.getName())
                        .surname(professional.getSurname())
                        .birthDate(professional.getBirthDate() != null ? professional.getBirthDate().toString() : null)
                        .address(professional.getAddress())
                        .username(professional.getUsername())
                        .phone(professional.getPhone())
                        .build())
                .toList();
    }

    public List<UserDto> getAllAdmins() {
        List<AppUser> admins = userRepository.findAllByRole(UserRole.ADMIN);
        return admins.stream()
                .map(admin -> UserDto.builder()
                        .id(admin.getId())
                        .dni(admin.getDni())
                        .name(admin.getName())
                        .surname(admin.getSurname())
                        .birthDate(admin.getBirthDate() != null ? admin.getBirthDate().toString() : null)
                        .address(admin.getAddress())
                        .username(admin.getUsername())
                        .phone(admin.getPhone())
                        .build())
                .toList();
    }

    public Optional<AppUser> findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public AppUser saveUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteClient(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("El cliente con id " + id + " no existe.");
        }
        userRepository.deleteById(id);
    }
}
