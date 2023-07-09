package com.example.Company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
* TO TEST PROGRAMS ABILITY TO JOIN TABLES
* insert employee into positions:
* positions/positionid/employeeid/
* */

@SpringBootApplication
public class CompanyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanyApplication.class, args);
    }

}
