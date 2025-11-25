package com.example.physiocore.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.physiocore.demo.dto.ClientDto;
import com.example.physiocore.demo.model.Client;
import com.example.physiocore.demo.repository.ClientRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<Client> findByUsername(String username) {
        return clientRepository.findByUsername(username);
    }

    public List<ClientDto> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(client -> ClientDto.builder()
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

    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Transactional
    public Client save(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        return clientRepository.save(client);
    }
}
