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
            
            userRepository.save(admin);
            System.out.println("Usuario inicial creado con éxito.");
        }
    }
}