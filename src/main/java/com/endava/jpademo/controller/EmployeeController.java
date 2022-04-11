package com.endava.jpademo.controller;

import com.endava.jpademo.dot.EmployeeLoginDto;
import com.endava.jpademo.entity.Employee;
import com.endava.jpademo.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = service.getAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        Employee employee = service.getById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping(params = "firstname")
    public ResponseEntity<List<Employee>> getEmployeesByFirstName(
            @RequestParam String firstname) {
        List<Employee> employees = service.getByFirstName(firstname);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping(params = "lastname")
    public ResponseEntity<List<Employee>> getEmployeesByLastName(
            @RequestParam String lastname) {
        List<Employee> employees = service.getByLastName(lastname);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/addOne")
    public ResponseEntity<Employee> insertEmployee(@RequestBody @Valid Employee newEmployee) {
        Employee employee = service.addOne(newEmployee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PostMapping("/addList")
    public ResponseEntity<List<Employee>> insertListOfEmployees(
            @RequestBody @Valid List<Employee> newEmployees) {
        List<Employee> employees = service.addList(newEmployees);
        return new ResponseEntity<>(employees, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @Valid @RequestBody Employee newEmployee,
            @PathVariable("id") Long id) {
        Employee employee = service.update(newEmployee, id);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") Long id) {
        String message = service.deleteById(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllEmployees() {
        String message = service.deleteAll();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody EmployeeLoginDto employeeLoginDto) {
        boolean employeeExists = service.login(employeeLoginDto);
        return new ResponseEntity<>(employeeExists, HttpStatus.OK);
    }

}
