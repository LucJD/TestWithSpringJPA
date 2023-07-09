package com.example.Company.Position;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PositionConfig {

    @Bean
    CommandLineRunner commandLineRunnerPosition(PositionRepository repository){
        return args -> {
            Position manager = new Position("Manager");
            Position backdev = new Position("Back-End Developer");
            Position fulldev = new Position("Full-Stack Developer");

            repository.saveAll(List.of(manager, backdev, fulldev));
        };
    }
}
