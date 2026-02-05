package com.example.physiocore.demo.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.physiocore.demo.model.AppUser;
import com.example.physiocore.demo.model.UserRole;
import com.example.physiocore.demo.repository.UserRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            // Creamos Admin
            AppUser admin = new AppUser();
            admin.setDni("X00000");
            admin.setName("Alex");
            admin.setSurname("Calafat");
            admin.setBirthDate(java.time.LocalDate.now());
            admin.setAddress("C/ Alex 1");
            admin.setUsername("alex@admin.com");
            admin.setPhone("123456789");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setActive(true);
            admin.setRoles(Set.of(UserRole.ADMIN));

            // Creamos un cliente de ejemplo
            AppUser client = new AppUser();
            client.setDni("Y00000");
            client.setName("John");
            client.setSurname("Doe");
            client.setBirthDate(java.time.LocalDate.now());
            client.setAddress("C/ John 1");
            client.setUsername("john@client.com");
            client.setPhone("987654321");
            client.setPassword(passwordEncoder.encode("123456"));
            client.setRoles(Set.of(UserRole.PATIENT));

            userRepository.save(admin);
            userRepository.save(client);
            System.out.println("Usuarios iniciales creados con éxito.");
        }
    }
}