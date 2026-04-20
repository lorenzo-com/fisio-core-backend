package com.example.physiocore.demo.config;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.physiocore.demo.model.AppUser;
import com.example.physiocore.demo.model.Appointment;
import com.example.physiocore.demo.model.ClinicService;
import com.example.physiocore.demo.model.StatusAppointment;
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
            admin.setBirthDate(LocalDate.parse("1974-01-15"));
            admin.setAddress("C/ Alex 1");
            admin.setUsername("alex@admin.com");
            admin.setPhone("123456789");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setActive(true);
            admin.setRoles(Set.of(UserRole.ADMIN));

            // Creamos dos trabajadores
            AppUser employee = new AppUser();
            employee.setDni("X00001");
            employee.setName("Maria");
            employee.setSurname("Garcia");
            employee.setBirthDate(LocalDate.parse("1978-08-20"));
            employee.setAddress("C/ Maria 2");
            employee.setUsername("maria@employee.com");
            employee.setPhone("123456789");
            employee.setPassword(passwordEncoder.encode("123456"));
            employee.setActive(true);
            employee.setRoles(Set.of(UserRole.PROFESSIONAL));
            
            AppUser employee2 = new AppUser();
            employee2.setDni("X00002");
            employee2.setName("Jose");
            employee2.setSurname("Hernandez");
            employee2.setBirthDate(LocalDate.parse("1983-09-14"));
            employee2.setAddress("C/ Jose 3");
            employee2.setUsername("jose@employee.com");
            employee2.setPhone("123456789");
            employee2.setPassword(passwordEncoder.encode("123456"));
            employee2.setActive(true);
            employee2.setRoles(Set.of(UserRole.PROFESSIONAL));

            // Creamos dos clientes
            AppUser client = new AppUser();
            client.setDni("Y00001");
            client.setName("John");
            client.setSurname("Doe");
            client.setBirthDate(LocalDate.parse("2000-12-16"));
            client.setAddress("C/ John 1");
            client.setUsername("john@client.com");
            client.setPhone("987654321");
            client.setPassword(passwordEncoder.encode("123456"));
            client.setRoles(Set.of(UserRole.PATIENT));

            AppUser client2 = new AppUser();
            client2.setDni("Y00002");
            client2.setName("Sarah");
            client2.setSurname("Smith");
            client2.setBirthDate(LocalDate.parse("2006-10-09"));
            client2.setAddress("C/ Sarah 2");
            client2.setUsername("sarah@client.com");
            client2.setPhone("987654321");
            client2.setPassword(passwordEncoder.encode("123456"));
            client2.setRoles(Set.of(UserRole.PATIENT));

            userRepository.save(admin);
            userRepository.save(employee);
            userRepository.save(employee2);
            userRepository.save(client);
            userRepository.save(client2);
            System.out.println("Usuarios iniciales creados con éxito.");

            // Obtenemos los usuarios
            AppUser john = userRepository.findByUsername("john@client.com").orElseThrow();
            AppUser sarah = userRepository.findByUsername("sarah@client.com").orElseThrow();
            AppUser maria = userRepository.findByUsername("maria@employee.com").orElseThrow();
            AppUser jose = userRepository.findByUsername("jose@employee.com").orElseThrow();

            // Creamos citas para esos clientes para ver paginación y filtro para profesionales
            Appointment appointment1 = new Appointment();
            appointment1.setDate("2026-06-15");
            appointment1.setHourValue("11:00");
            appointment1.setService(ClinicService.FISIOTERAPIA_DEPORTIVA);
            appointment1.setPatient(john);
            appointment1.setProfessional(maria);

            Appointment appointment2 = new Appointment();
            appointment2.setDate("2026-06-12");
            appointment2.setHourValue("12:00");
            appointment2.setService(ClinicService.REHABILITACION);
            appointment2.setState(StatusAppointment.CANCELADA);
            appointment2.setPatient(john);
            appointment2.setProfessional(maria);

            Appointment appointment3 = new Appointment();
            appointment3.setDate("2026-05-04");
            appointment3.setHourValue("13:00");
            appointment3.setService(ClinicService.MASAJE_TERAPEUTICO);
            appointment3.setState(StatusAppointment.COMPLETADA);
            appointment3.setPatient(john);
            appointment3.setProfessional(maria);

            Appointment appointment4 = new Appointment();
            appointment4.setDate("2026-05-11");
            appointment4.setHourValue("14:00");
            appointment4.setService(ClinicService.ESCUELA_ESPALDA);
            appointment4.setState(StatusAppointment.AUSENTE);
            appointment4.setPatient(john);
            appointment4.setProfessional(maria);

            Appointment appointment5 = new Appointment();
            appointment5.setDate("2026-06-17");
            appointment5.setHourValue("15:00");
            appointment5.setService(ClinicService.FISIOTERAPIA_DEPORTIVA);
            appointment5.setPatient(john);
            appointment5.setProfessional(maria);

            Appointment appointment6 = new Appointment();
            appointment6.setDate("2026-06-19");
            appointment6.setHourValue("16:00");
            appointment6.setService(ClinicService.REHABILITACION);
            appointment6.setState(StatusAppointment.CANCELADA);
            appointment6.setPatient(john);
            appointment6.setProfessional(maria);

            Appointment appointment7 = new Appointment();
            appointment7.setDate("2026-05-06");
            appointment7.setHourValue("17:00");
            appointment7.setService(ClinicService.MASAJE_TERAPEUTICO);
            appointment7.setState(StatusAppointment.COMPLETADA);
            appointment7.setPatient(john);
            appointment7.setProfessional(maria);

            Appointment appointment8 = new Appointment();
            appointment8.setDate("2026-05-13");
            appointment8.setHourValue("18:00");
            appointment8.setService(ClinicService.ESCUELA_ESPALDA);
            appointment8.setState(StatusAppointment.AUSENTE);
            appointment8.setPatient(john);
            appointment8.setProfessional(maria);

            Appointment appointment9 = new Appointment();
            appointment9.setDate("2026-06-22");
            appointment9.setHourValue("19:00");
            appointment9.setService(ClinicService.FISIOTERAPIA_DEPORTIVA);
            appointment9.setPatient(john);
            appointment9.setProfessional(maria);

            Appointment appointment10 = new Appointment();
            appointment10.setDate("2026-06-12");
            appointment10.setHourValue("11:00");
            appointment10.setService(ClinicService.REHABILITACION);
            appointment10.setState(StatusAppointment.CANCELADA);
            appointment10.setPatient(john);
            appointment10.setProfessional(maria);

            Appointment appointment11 = new Appointment();
            appointment11.setDate("2026-05-08");
            appointment11.setHourValue("12:00");
            appointment11.setService(ClinicService.MASAJE_TERAPEUTICO);
            appointment11.setState(StatusAppointment.COMPLETADA);
            appointment11.setPatient(john);
            appointment11.setProfessional(maria);

            Appointment appointment12 = new Appointment();
            appointment12.setDate("2026-05-15");
            appointment12.setHourValue("13:00");
            appointment12.setService(ClinicService.ESCUELA_ESPALDA);
            appointment12.setState(StatusAppointment.AUSENTE);
            appointment12.setPatient(john);
            appointment12.setProfessional(maria);

            Appointment appointment13 = new Appointment();
            appointment13.setDate("2026-06-16");
            appointment13.setHourValue("11:00");
            appointment13.setService(ClinicService.FISIOTERAPIA_DEPORTIVA);
            appointment13.setPatient(sarah);
            appointment13.setProfessional(jose);

            Appointment appointment14 = new Appointment();
            appointment14.setDate("2026-05-12");
            appointment14.setHourValue("12:00");
            appointment14.setService(ClinicService.REHABILITACION);
            appointment14.setState(StatusAppointment.CANCELADA);            
            appointment14.setPatient(sarah);
            appointment14.setProfessional(jose);

            Appointment appointment15 = new Appointment();
            appointment15.setDate("2026-05-19");
            appointment15.setHourValue("13:00");
            appointment15.setService(ClinicService.MASAJE_TERAPEUTICO);
            appointment15.setState(StatusAppointment.COMPLETADA);
            appointment15.setPatient(sarah);
            appointment15.setProfessional(jose);

            Appointment appointment16 = new Appointment();
            appointment16.setDate("2026-05-20");
            appointment16.setHourValue("14:00");
            appointment16.setService(ClinicService.ESCUELA_ESPALDA);
            appointment16.setState(StatusAppointment.AUSENTE);
            appointment16.setPatient(sarah);
            appointment16.setProfessional(jose);

            Appointment appointment17 = new Appointment();
            appointment17.setDate("2026-06-18");
            appointment17.setHourValue("15:00");
            appointment17.setService(ClinicService.FISIOTERAPIA_DEPORTIVA);
            appointment17.setPatient(sarah);
            appointment17.setProfessional(jose);

            Appointment appointment18 = new Appointment();
            appointment18.setDate("2026-05-14");
            appointment18.setHourValue("16:00");
            appointment18.setService(ClinicService.MASAJE_TERAPEUTICO);
            appointment18.setState(StatusAppointment.CANCELADA);
            appointment18.setPatient(sarah);
            appointment18.setProfessional(jose);

            appoinmentRepository.save(appointment1);
            appoinmentRepository.save(appointment2);
            appoinmentRepository.save(appointment3);
            appoinmentRepository.save(appointment4);
            appoinmentRepository.save(appointment5);
            appoinmentRepository.save(appointment6);
            appoinmentRepository.save(appointment7);
            appoinmentRepository.save(appointment8);
            appoinmentRepository.save(appointment9);
            appoinmentRepository.save(appointment10);
            appoinmentRepository.save(appointment11);
            appoinmentRepository.save(appointment12);
            appoinmentRepository.save(appointment13);
            appoinmentRepository.save(appointment14);
            appoinmentRepository.save(appointment15);
            appoinmentRepository.save(appointment16);
            appoinmentRepository.save(appointment17);
            appoinmentRepository.save(appointment18);
            System.out.println("Reservas iniciales creadas con éxito.");
        }
    }
}