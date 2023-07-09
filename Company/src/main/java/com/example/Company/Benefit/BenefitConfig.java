package com.example.Company.Benefit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class BenefitConfig {
    @Bean
    CommandLineRunner commandLineRunnerBenefit(BenefitRepository repository){
        return args -> {
            Benefit dental = new Benefit("Dental");
            Benefit _401k = new Benefit("401K");
            Benefit vision = new Benefit("Vision");
            Benefit vacationTime = new Benefit("Vacation Time");

            repository.saveAll(List.of(dental, _401k, vision, vacationTime));
        };
    }

}
