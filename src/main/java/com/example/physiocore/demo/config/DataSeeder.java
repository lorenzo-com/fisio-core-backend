package com.example.physiocore.demo.config;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.physiocore.demo.model.AppUser;
import com.example.physiocore.demo.model.Appointment;
import com.example.physiocore.demo.model.ClinicService;
import com.example.physiocore.demo.model.UserRole;
import com.example.physiocore.demo.repository.AppoinmentRepository;
import com.example.physiocore.demo.repository.UserRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppoinmentRepository appoinmentRepository;

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

            // Creamos Trabajador
            AppUser employee = new AppUser();
            employee.setDni("X00001");
            employee.setName("Maria");
            employee.setSurname("Garcia");
            employee.setBirthDate(java.time.LocalDate.now());
            employee.setAddress("C/ Maria 2");
            employee.setUsername("maria@employee.com");
            employee.setPhone("123456789");
            employee.setPassword(passwordEncoder.encode("123456"));
            employee.setActive(true);
            employee.setRoles(Set.of(UserRole.PROFESSIONAL));

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
            userRepository.save(employee);
            System.out.println("Usuarios iniciales creados con éxito.");

            // Obtenemos los usuarios
            AppUser john = userRepository.findByUsername("john@client.com").orElseThrow();
            // Creamos citas para ese cliente para ver paginación
            // Appointment appointment1 = new Appointment();
            // appointment1.setDate(new Date().toString());
            // appointment1.setHourValue("11:00");
            // appointment1.setService("Fisioterapia Deportiva");
            // appointment1.setPatient(john);

            // Appointment appointment2 = new Appointment();
            // appointment2.setDate(new Date().toString());
            // appointment2.setHourValue("12:00");
            // appointment2.setService(ClinicService.REHABILITACION);
            // appointment2.setPatient(john);

            // Appointment appointment3 = new Appointment();
            // appointment3.setDate(new Date().toString());
            // appointment3.setHourValue("13:00");
            // appointment3.setService("Masaje terapéutico");
            // appointment3.setPatient(john);

            // Appointment appointment4 = new Appointment();
            // appointment4.setDate(new Date().toString());
            // appointment4.setHourValue("14:00");
            // appointment4.setService("Fisioterapia Deportiva");
            // appointment4.setPatient(john);

            // Appointment appointment5 = new Appointment();
            // appointment5.setDate(new Date().toString());
            // appointment5.setHourValue("15:00");
            // appointment5.setService("Rehabilitación");
            // appointment5.setPatient(john);

            // Appointment appointment6 = new Appointment();
            // appointment6.setDate(new Date().toString());
            // appointment6.setHourValue("16:00");
            // appointment6.setService("Masaje terapéutico");
            // appointment6.setPatient(john);

            // Appointment appointment7 = new Appointment();
            // appointment7.setDate(new Date().toString());
            // appointment7.setHourValue("17:00");
            // appointment7.setService("Fisioterapia Deportiva");
            // appointment7.setPatient(john);

            // Appointment appointment8 = new Appointment();
            // appointment8.setDate(new Date().toString());
            // appointment8.setHourValue("18:00");
            // appointment8.setService("Rehabilitación");
            // appointment8.setPatient(john);

            // Appointment appointment9 = new Appointment();
            // appointment9.setDate(new Date().toString());
            // appointment9.setHourValue("19:00");
            // appointment9.setService("Masaje terapéutico");
            // appointment9.setPatient(john);

            // Appointment appointment10 = new Appointment();
            // appointment10.setDate(new Date().toString());
            // appointment10.setHourValue("11:00");
            // appointment10.setService("Fisioterapia Deportiva");
            // appointment10.setPatient(john);

            // Appointment appointment11 = new Appointment();
            // appointment11.setDate(new Date().toString());
            // appointment11.setHourValue("12:00");
            // appointment11.setService("Rehabilitación");
            // appointment11.setPatient(john);

            // Appointment appointment12 = new Appointment();
            // appointment12.setDate(new Date().toString());
            // appointment12.setHourValue("13:00");
            // appointment12.setService("Masaje terapéutico");
            // appointment12.setPatient(john);

            // appoinmentRepository.save(appointment1);
            // appoinmentRepository.save(appointment2);
            // appoinmentRepository.save(appointment3);
            // appoinmentRepository.save(appointment4);
            // appoinmentRepository.save(appointment5);
            // appoinmentRepository.save(appointment6);
            // appoinmentRepository.save(appointment7);
            // appoinmentRepository.save(appointment8);
            // appoinmentRepository.save(appointment9);
            // appoinmentRepository.save(appointment10);
            // appoinmentRepository.save(appointment11);
            // appoinmentRepository.save(appointment12);
            System.out.println("Reservas iniciales creadas con éxito.");
        }
    }
}