package com.endava.jpademo.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("Could not find user with ID: " + id);
    }
    public EmployeeNotFoundException(String email){
        super("Could not find employee with email: " + email);
    }
}
