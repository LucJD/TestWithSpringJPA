package com.example.Company.Employee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EmployeeConfig {

    @Bean
    CommandLineRunner commandLineRunnerEmployee(EmployeeRepository repository){
        return args -> {
            Employee lucas = new Employee("Lucas");
            Employee jacob = new Employee("Jacob");
            Employee conner = new Employee("Conner");

            repository.saveAll(List.of(lucas, jacob, conner));
        };
    }
}
