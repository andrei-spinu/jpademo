package com.endava.jpademo;

import com.endava.jpademo.entity.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityNotFoundException;

@SpringBootApplication
public class JpademoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JpademoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        
    }
}
